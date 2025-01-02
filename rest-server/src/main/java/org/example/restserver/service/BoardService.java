package org.example.restserver.service;

import org.example.restserver.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;

@Service
public class BoardService {


    @Autowired
    private BoardRepository boardRepository;


    public Page<Community> getPost(int pageNumber){

    }
}