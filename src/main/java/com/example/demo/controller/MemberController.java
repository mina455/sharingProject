package com.example.demo.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.View;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.service.MemberService;
import com.example.demo.vo.MemberVo;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Value("${paiq.attachment.public.path}")
    private String attachmentPublicPath;
    
    @Autowired
    MemberService memberService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, @ModelAttribute(value = "params") MemberVo params) {

        List<MemberVo> list = memberService.list(params);
        model.addAttribute("list", list);
        model.addAttribute("params", params);

        return "/member/list";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model, @ModelAttribute(value = "params") MemberVo params) {

        MemberVo entity = new MemberVo();
        model.addAttribute("memberVo", entity);

        return "/member/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Model model
            , HttpSession session
            , RedirectAttributes redirectAttributes
            , @ModelAttribute(value = "params") @Valid MemberVo params
            , BindingResult bindingResult
            , SessionStatus sessionStatus
            , @RequestPart MultipartFile img) {

        File uploadedImage = doAttach(img);
        String uploadedPath = uploadedImage.getAbsolutePath();
        params.setImage(uploadedPath);
        
        if (bindingResult.hasErrors()) {
            MemberVo entity = new MemberVo();
            model.addAttribute("params", entity);
            return "/member/add";
        }

        memberService.add(params);

        sessionStatus.setComplete();
        /*flashMessage(redirectAttributes, "등록 되었습니다.");*/
        return "redirect:/member/list";
    }


    public File doAttach(MultipartFile sourceFile) {
        String fileBaseName = UUID.randomUUID().toString();

        String sourceFileName = sourceFile.getOriginalFilename();
        String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
        File destinationFile;
        String destinationFileName;
        do {
            destinationFileName = fileBaseName + "." + sourceFileNameExtension;
            destinationFile = new File(attachmentPublicPath + destinationFileName);
        }
        while (destinationFile.exists());
        destinationFile.getParentFile().mkdirs();

        try {
            sourceFile.transferTo(destinationFile);
            System.out.println("Upload File " + sourceFile.getOriginalFilename() + " Move To " + destinationFile.getPath());
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return destinationFile;
    }
    
    @RequestMapping("/thumbnail")
    public void thumbnail(
            HttpServletResponse response,
            HttpServletRequest request,
            @RequestParam(value = "seq") int seq) throws IOException {

        MemberVo atchFileVo = memberService.get(seq);
        if (null == atchFileVo) {
            return;
        }

        File file = new File(atchFileVo.getImage());

        if (!file.exists()) {
            // no file
        }

        String mimeType = URLConnection.guessContentTypeFromName(file.getName());

        if (mimeType == null) {
            //
        }

        response.setContentType(mimeType);

        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));

        response.setContentLength((int)file.length());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

        FileCopyUtils.copy(inputStream, response.getOutputStream());

    }
}
