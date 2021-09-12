package com.example.springbootstudyex3.controller;

import com.example.springbootstudyex3.dto.SampleDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Controller
@RequestMapping("/sample")
@Log4j2
public class SampleController {

    @GetMapping("/ex1")
    public void ex1() {
        log.info("ex1..............");
    }

    @GetMapping({"/ex2"})
    public String   exModel(Model model) {
        List<SampleDto> sampleDtoList = createSampleDtoList();

        model.addAttribute("list", sampleDtoList);
        return "/sample/ex2";
    }

    private List<SampleDto> createSampleDtoList() {
        List<SampleDto> sampleDtoList = LongStream.rangeClosed(1, 20)
                .mapToObj(
                        value -> SampleDto.builder()
                                .sno(value)
                                .first("First" + value)
                                .last("Last" + value)
                                .regDate(LocalDateTime.now())
                                .build()
                )
                .collect(Collectors.toList());
        return sampleDtoList;
    }

    /**
     * javascript 에 적용되는 th:inline 속성을 확인하기 위한 예제
     *
     * @author Gumin Jeong
     * @since 2021-09-11
     */
    @GetMapping("/exInline")
    public String exInline(RedirectAttributes redirectAttributes) {
        log.info("exInline................");

        SampleDto dto = SampleDto.builder()
                .sno(100L)
                .first("First...100")
                .last("Last...100")
                .regDate(LocalDateTime.now())
                .build();

        redirectAttributes.addFlashAttribute("result", "success");
        redirectAttributes.addFlashAttribute("dto", dto);

        return "redirect:/sample/ex3";
    }

    @GetMapping("/ex3")
    public String ex3() {
        log.info("ex3............");
        return "/sample/ex3";
    }

    @GetMapping("/exLink")
    public String exLink(Model model) {
        model.addAttribute("list", createSampleDtoList());
        return "/sample/exLink";
    }

    @GetMapping("/layout1")
    public String layout1() {
        log.info("exLayout1..................");
        return "/sample/layout1";
    }

    @GetMapping("/layout2")
    public String layout2() {
        log.info("exLayout2..........");
        return "/sample/layout2";
    }

    @GetMapping("/exTemplate")
    public String  exTemplate() {
        log.info("exTemplate");

        return "/sample/exTemplate";
    }

    @GetMapping("/exSidebar")
    public String esSidebar() {
        return "/sample/exSidebar";
    }
}
