package com.example.guestbook.contoroller;


import com.example.guestbook.dto.GuestBookDto;
import com.example.guestbook.dto.PageRequestDto;
import com.example.guestbook.dto.PageResultResponseDto;
import com.example.guestbook.entity.GuestBook;
import com.example.guestbook.service.GuestBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/guestbook")
@RequiredArgsConstructor
@Log4j2
public class GuestBookController {

    private final GuestBookService guestBookService;

    @GetMapping("/")
    public String index() {
        return "redirect:/guestbook/list";
    }

    @GetMapping("/list")
    public String list(
            GuestBookDto.Request.SearchList requestDto,
            Model model
    ) {
        log.info("list..........."+requestDto);
        PageResultResponseDto<GuestBook> result = guestBookService.getList(requestDto);
        PageResultResponseDto<GuestBookDto.Response.Simple> realResult = result.convert(GuestBookDto.Response.Simple::of);
        model.addAttribute("result", realResult);

        System.out.println("realResult = " + realResult);

        return "guestbook/list";
    }

    @GetMapping("/register")
    public String register() {
        log.info("register get ...");
        return "guestbook/register";
    }

    @PostMapping("/register")
    public String registerPost(
            GuestBookDto.Request.Register requestDto,
            RedirectAttributes redirectAttributes
    ) {
        log.info("dto... {}" ,requestDto);

        Long gno = guestBookService.register(requestDto);
        redirectAttributes.addFlashAttribute("msg", gno);

        return "redirect:/guestbook/list";
    }

    @GetMapping("/read")
    public String read(
            Long gno,
            @ModelAttribute GuestBookDto.Request.SearchList requestDto,
            Model model
    ) {
        log.info("gno: {}", gno);
        GuestBook guestBook = guestBookService.getOne(gno);
        model.addAttribute("dto", GuestBookDto.Response.Detail.of(guestBook));
        model.addAttribute("requestDto", requestDto);

        return "/guestbook/read";
    }

    @GetMapping("/modify")
    public String modifyForm(
            Long gno,
            @ModelAttribute GuestBookDto.Request.SearchList requestDto,
            Model model
    ) {
        log.info("gno: {}", gno);
        GuestBook guestBook = guestBookService.getOne(gno);
        model.addAttribute("dto", GuestBookDto.Response.Detail.of(guestBook));
        model.addAttribute("requestDto", requestDto);

        return "/guestbook/modify";
    }

//    @PostMapping("/modify")
//    public String modifyAction(
//            @RequestBody
//    ) {
//        return null;
//    }

    @PostMapping("/remove")
    public String remove(Long gno, RedirectAttributes redirectAttributes) {
        log.info("gno: {}", gno);
        guestBookService.remove(gno);

        redirectAttributes.addFlashAttribute("msg", gno);

        return "redirect:/guestbook/list";
    }
}
