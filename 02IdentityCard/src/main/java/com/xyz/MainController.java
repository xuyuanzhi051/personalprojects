package com.xyz;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {

    @Value("${file.uplaod.path}")
    private String uploadDir;
    @Autowired
    private OcrService ocrService;

    private List<String> faceImages = new ArrayList<>();
    private List<String> backImages = new ArrayList<>();
    private List<Map<String, String>> faceResults = new ArrayList<>();
    private List<Map<String, String>> backResults = new ArrayList<>();

    @RequestMapping("/index")
    public String index(Model model) {
        if (faceImages.size() != backImages.size()) {
            clearAllList();
        }
        if (!CollectionUtils.isEmpty(faceImages) && faceImages.size() == backImages.size()) {
            model.addAttribute("faceImage", faceImages.get(faceImages.size() - 1));
            model.addAttribute("faceResult", faceResults.get(faceResults.size() - 1));
            model.addAttribute("backImage", backImages.get(backImages.size() - 1));
            model.addAttribute("backResult", backResults.get(backResults.size() - 1));
        }
        return "index";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("face") MultipartFile face, @RequestParam("back") MultipartFile back,
                         RedirectAttributes redirectAttributes) {
        if (face.isEmpty() || back.isEmpty()) {
            redirectAttributes.addFlashAttribute("messages", "请选择一个文件进行上传。");
            return "redirect:/index";
        }
        String errorMessages = null;
        Path dir = Paths.get(uploadDir);
        if (!Files.exists(dir)) {
            try {
                Files.createDirectories(dir);
            } catch (IOException e) {
                e.printStackTrace();
                errorMessages += e.getMessage() + "\n";
            }
        }
        try {
            if (!face.isEmpty()) {
                String filename = saveFile(face);
                Map<String, String> faceResult = ocrService.RecognizeIdCard(uploadDir + filename, "face");
                faceImages.add("/images/" + filename);
                faceResults.add(faceResult);
            }
            if (!back.isEmpty()) {
                String filename = saveFile(back);
                Map<String, String> faceResult = ocrService.RecognizeIdCard(uploadDir + filename, "back");
                backImages.add("/images/" + filename);
                backResults.add(faceResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorMessages += e.getMessage() + "\n";
        }
        if (StringUtils.isNoneBlank(errorMessages)) {
            redirectAttributes.addFlashAttribute("messages", errorMessages);
        }
        return "redirect:/index";
    }

    private void clearAllList() {
        faceImages.clear();
        backImages.clear();
        faceResults.clear();
        backResults.clear();
    }

    public String saveFile(MultipartFile file) {
        String filename = UUID.randomUUID().toString() + "."
                + StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
        Path path = Paths.get(uploadDir + filename);
        System.out.println(faceResults);
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            return null;
        }
        return filename;
    }
}