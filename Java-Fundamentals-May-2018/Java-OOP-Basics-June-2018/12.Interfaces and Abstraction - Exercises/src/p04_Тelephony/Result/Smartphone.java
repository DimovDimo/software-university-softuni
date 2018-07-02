

public class Smartphone implements CallOtherPhones, BrowseInTheWorldWideWeb {
    public Smartphone() {
    }

    @Override
    public String Browsing(String site) {
        boolean isInValit = false;
        char[] siteCharacters = site.toCharArray();
        for (char siteCharacter : siteCharacters) {
            if (Character.isDigit(siteCharacter)){
                isInValit = true;
                break;
            }
        }

        if (isInValit){
            return "Invalid URL!";
        } else {
            return String.format("Browsing: %s!", site);
        }
    }

    @Override
    public String Calling(String phone) {
        boolean isInValit = false;
        char[] phoneCharacters = phone.toCharArray();
        for (char phoneCharacter : phoneCharacters) {
            if (Character.isDigit(phoneCharacter) == false){
                isInValit = true;
                break;
            }
        }

        if (isInValit){
            return "Invalid number!";
        } else {
            return "Calling... " + phone;
        }
    }
}
