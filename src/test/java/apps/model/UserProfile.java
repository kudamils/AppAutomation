package apps.model;

public class UserProfile {

    private static int id;
    private static String name;
    private static String phone;
    private static String email;
    private static String address;
    private static String province;
    private static String city;
    private static String area;
    private static String district;
    private static String post_code;
    private static String notes;
    private static String latitude;
    private static String longitude;
    private static int remote_id;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        UserProfile.id = id;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        UserProfile.name = name;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        UserProfile.phone = phone;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        UserProfile.email = email;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        UserProfile.address = address;
    }

    public static String getProvince() {
        return province;
    }

    public static void setProvince(String province) {
        UserProfile.province = province;
    }

    public static String getCity() {
        return city;
    }

    public static void setCity(String city) {
        UserProfile.city = city;
    }

    public static String getArea() {
        return area;
    }

    public static void setArea(String area) {
        UserProfile.area = area;
    }

    public static String getDistrict() {
        return district;
    }

    public static void setDistrict(String district) {
        UserProfile.district = district;
    }

    public static String getPost_code() {
        return post_code;
    }

    public static void setPost_code(String post_code) {
        UserProfile.post_code = post_code;
    }

    public static String getNotes() {
        return notes;
    }

    public static void setNotes(String notes) {
        UserProfile.notes = notes;
    }

    public static String getLatitude() {
        return latitude;
    }

    public static void setLatitude(String latitude) {
        UserProfile.latitude = latitude;
    }

    public static String getLongitude() {
        return longitude;
    }

    public static void setLongitude(String longitude) {
        UserProfile.longitude = longitude;
    }

    public static int getRemote_id() {
        return remote_id;
    }

    public static void setRemote_id(int remote_id) {
        UserProfile.remote_id = remote_id;
    }

    public String toString() {
        return getId() + getName() + getPhone() + getEmail() + getAddress() + getCity() + getAddress() + getDistrict() + getPost_code() + getNotes() + getLatitude() + getLongitude() + getRemote_id();
    }
}
