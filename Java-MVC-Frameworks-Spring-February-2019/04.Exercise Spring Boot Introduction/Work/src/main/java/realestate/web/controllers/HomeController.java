package realestate.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import realestate.domain.view.OfferViewModel;
import realestate.service.OfferService;
import realestate.util.HtmlReader;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final OfferService offerService;
    private final HtmlReader htmlReader;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeController(OfferService offerService, HtmlReader htmlReader, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.htmlReader = htmlReader;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @ResponseBody
    public String index() throws IOException {
         return this.prepareHtml();
    }

    private String prepareHtml() throws IOException {
        List<OfferViewModel> offers = this.offerService.findAllOffers()
                .stream()
                .map(o -> this.modelMapper.map(o, OfferViewModel.class))
                .collect(Collectors.toList());

        StringBuilder offersHtml = new StringBuilder();
        if(offers.size() == 0){
            offersHtml
                    .append("<div class=\"apartment\" style=\"border: 2px solid red;\">")
                    .append("There aren't any offers!")
                    .append("</div>");

            return this.htmlReader
                    .readHtml("C:\\Users\\User\\Desktop\\Java\\src\\main\\resources\\static\\index.html")
                    .replace("{{offers}}", offersHtml.toString().trim());
        }

        for (OfferViewModel offer : offers) {
            offersHtml
                    .append("<div class=\"apartment\">")
                    .append(String.format("<p>Rent: %.2f</p>", offer.getApartmentRent()))
                    .append(String.format("<p>Type: %s</p>", offer.getApartmentType()))
                    .append(String.format("<p>Commission: %.2f</p>", offer.getAgencyCommission()))
                    .append("</div>")
                    .append(System.lineSeparator());
        }

        return this.htmlReader
                .readHtml("C:\\Users\\User\\Desktop\\Java\\src\\main\\resources\\static\\index.html")
                .replace("{{offers}}", offersHtml.toString().trim());
    }
}
