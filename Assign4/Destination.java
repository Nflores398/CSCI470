public class Destination {
    String cityName;
    int normalMileage;
    int superSaverMileage;
    int additionalMileage;
    int offSeasonlow;
    int offSeasonHigh;

    Destination(String name, int mileage, int superSaver, int additionalMileage, int offSeasonlower,
            int offSeasonHigh) {
        this.cityName = name;
        this.normalMileage = mileage;
        this.superSaverMileage = superSaver;
        this.additionalMileage = additionalMileage;
        this.offSeasonlow = offSeasonlower;
        this.offSeasonHigh = offSeasonHigh;
    }


    public String getCityName()
    {
        return this.cityName;
    }
    public int getNormalMiles() {
        return this.normalMileage;
    }

    public int getSuperSaverMileage() {
        return this.superSaverMileage;
    }

    public int getAdditionalMileage() {
        return this.additionalMileage;
    }

    public int getOffSeasonlow() {
        return this.offSeasonlow;
    }

    public int getOffSeasonHigh() {
        return this.offSeasonHigh;
    }
}
