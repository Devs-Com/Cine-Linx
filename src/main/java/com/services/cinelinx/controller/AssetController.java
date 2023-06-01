package com.services.cinelinx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.services.cinelinx.service.FileSystemStorageService;

@RestController
@RequestMapping("/assets")
public class AssetController {
    @Autowired
    private FileSystemStorageService fileSystemStorageService;

    @GetMapping("/{filename:.+}")
    Resource getResource(@PathVariable("filename") String filename) {
        return fileSystemStorageService.loadAsResource(filename);
    }
}
