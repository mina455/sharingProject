package com.example.demo.dao;

import java.util.List;

import com.example.demo.vo.MemberVo;

public interface MemberMapper {

    List<MemberVo> list(MemberVo vo);
    int add(MemberVo vo);
    MemberVo get(String username);

}
