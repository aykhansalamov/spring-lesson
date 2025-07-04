package org.example.springlesson.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.springlesson.service.HelloService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelloServiceImpl implements HelloService {

    @Override
    public String printHello() {
        return "hello";
    }
}
