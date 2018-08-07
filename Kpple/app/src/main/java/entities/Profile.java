package entities;

public class Profile {
    private String couple1Name;
    private String couple2Name;
    private String mail1;
    private String mail2;
    private String genderC1;
    private String genderC2;
    private String profileName;
    private String profileMail;

    public Profile() {
    }

    public Profile(String couple1Name, String couple2Name, String mail1, String mail2,
                   String genderC1, String genderC2, String profileName, String profileMail) {
        this.couple1Name = couple1Name;
        this.couple2Name = couple2Name;
        this.mail1 = mail1;
        this.mail2 = mail2;
        this.genderC1 = genderC1;
        this.genderC2 = genderC2;
        this.profileName = profileName;
        this.profileMail = profileMail;
    }

    public String getCouple1Name() {
        return couple1Name;
    }

    public void setCouple1Name(String couple1Name) {
        this.couple1Name = couple1Name;
    }

    public String getCouple2Name() {
        return couple2Name;
    }

    public void setCouple2Name(String couple2Name) {
        this.couple2Name = couple2Name;
    }

    public String getMail1() {
        return mail1;
    }

    public void setMail1(String mail1) {
        this.mail1 = mail1;
    }

    public String getMail2() {
        return mail2;
    }

    public void setMail2(String mail2) {
        this.mail2 = mail2;
    }

    public String getGenderC1() {
        return genderC1;
    }

    public void setGenderC1(String genderC1) {
        this.genderC1 = genderC1;
    }

    public String getGenderC2() {
        return genderC2;
    }

    public void setGenderC2(String genderC2) {
        this.genderC2 = genderC2;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileMail() {
        return profileMail;
    }

    public void setProfileMail(String profileMail) {
        this.profileMail = profileMail;
    }
}
