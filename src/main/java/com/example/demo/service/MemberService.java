package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberMapper;
import com.example.demo.vo.MemberVo;

@Service
public class MemberService {

    @Autowired
    MemberMapper memberMapper;
    
    public List<MemberVo> list(MemberVo vo) {
        return memberMapper.list(vo);
    }

    public void add(MemberVo vo) {
        memberMapper.add(vo);
    }
    
    public MemberVo get(String username) {
        return memberMapper.get(username);
    }
    
}
