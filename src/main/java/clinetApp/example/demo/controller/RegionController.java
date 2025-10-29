package clinetApp.example.demo.controller;

import clinetApp.example.demo.model.Region;
import clinetApp.example.demo.service.RegionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/regions")
public class RegionController {

    private final RegionService service;

    @GetMapping
    public String list(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "20") int size,
                       Model model) {
        Page<Region> data = service.findAll(PageRequest.of(page, size));
        model.addAttribute("regions", data.getContent());
        model.addAttribute("page", data);
        return "regions/list";
    }

    // @GetMapping("/new")
    // public String createForm(Model model) {
    //     model.addAttribute("region", new Region());
    //     model.addAttribute("title", "Add Region");
    //     return "regions/form";
    // }

    @GetMapping("/new")
public String createForm(Model model) {
    model.addAttribute("region", new Region());
    model.addAttribute("title", "Add Region");
    model.addAttribute("readonly", false);
    return "regions/form";
}


// detail tetap readonly=true


    // @PostMapping
    // public String create(@Valid @ModelAttribute("region") Region region, BindingResult br) {
    //     if (br.hasErrors()) return "regions/form";
    //     service.save(region);
    //     return "redirect:/regions";
    // }

    @PostMapping
public String create(@Valid @ModelAttribute("region") Region region,
                     BindingResult br,
                     org.springframework.web.servlet.mvc.support.RedirectAttributes ra) {
    if (br.hasErrors()) return "regions/form";
    service.save(region);
    ra.addFlashAttribute("flash", "Region berhasil ditambahkan.");
    return "redirect:/regions";
}

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Region r = service.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID"));
        model.addAttribute("region", r);
        model.addAttribute("readonly", true);
        model.addAttribute("title", "Detail Region");
        return "regions/form";
    }

    // @GetMapping("/{id}/edit")
    // public String editForm(@PathVariable Long id, Model model) {
    //     Region r = service.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID"));
    //     model.addAttribute("region", r);
    //     model.addAttribute("title", "Update Region");
    //     return "regions/form";
    // }

        @GetMapping("/{id}/edit")
public String editForm(@PathVariable Long id, Model model) {
    Region r = service.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID"));
    model.addAttribute("region", r);
    model.addAttribute("title", "Update Region");
    model.addAttribute("readonly", false);
    return "regions/form";
}

    // @PostMapping("/{id}")
    // public String update(@PathVariable Long id, @Valid @ModelAttribute("region") Region region, BindingResult br) {
    //     if (br.hasErrors()) return "regions/form";
    //     region.setId(id);
    //     service.save(region);
    //     return "redirect:/regions";
    // }

    @PostMapping("/{id}")
public String update(@PathVariable Long id,
                     @Valid @ModelAttribute("region") Region region,
                     BindingResult br,
                     org.springframework.web.servlet.mvc.support.RedirectAttributes ra) {
    if (br.hasErrors()) return "regions/form";
    region.setId(id);
    service.save(region);
    ra.addFlashAttribute("flash", "Region berhasil diperbarui.");
    return "redirect:/regions";
}

    // @PostMapping("/{id}/delete")
    // public String delete(@PathVariable Long id) {
    //     service.deleteById(id);
    //     return "redirect:/regions";
    // }
    @PostMapping("/{id}/delete")
public String delete(@PathVariable Long id,
                     org.springframework.web.servlet.mvc.support.RedirectAttributes ra) {
    service.deleteById(id);
    ra.addFlashAttribute("flash", "Region terhapus.");
    return "redirect:/regions";
}
}
