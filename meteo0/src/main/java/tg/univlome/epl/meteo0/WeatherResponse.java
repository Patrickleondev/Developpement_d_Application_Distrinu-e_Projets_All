package tg.univlome.epl.meteo0;

import java.io.Serializable;
import java.util.List;

/**
 * Classe principale de réponse météo respectant les conventions JavaBean
 * @author techwizard
 */
public class WeatherResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Request request;
    private Location location;
    private Current current;

    // Constructeur par défaut
    public WeatherResponse() {
    }

    // Getters et Setters
    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    /**
     * Classe Request respectant les conventions JavaBean
     */
    public static class Request implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private String type;
        private String query;
        private String language;
        private String unit;

        // Constructeur par défaut
        public Request() {
        }

        // Getters et Setters
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        @Override
        public String toString() {
            return "Request{" +
                    "type='" + type + '\'' +
                    ", query='" + query + '\'' +
                    ", language='" + language + '\'' +
                    ", unit='" + unit + '\'' +
                    '}';
        }
    }

    /**
     * Classe Location respectant les conventions JavaBean
     */
    public static class Location implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private String name;
        private String country;
        private String region;
        private String lat;
        private String lon;
        private String timezone_id;
        private String localtime;
        private long localtime_epoch;
        private String utc_offset;

        // Constructeur par défaut
        public Location() {
        }

        // Getters et Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getTimezone_id() {
            return timezone_id;
        }

        public void setTimezone_id(String timezone_id) {
            this.timezone_id = timezone_id;
        }

        public String getLocaltime() {
            return localtime;
        }

        public void setLocaltime(String localtime) {
            this.localtime = localtime;
        }

        public long getLocaltime_epoch() {
            return localtime_epoch;
        }

        public void setLocaltime_epoch(long localtime_epoch) {
            this.localtime_epoch = localtime_epoch;
        }

        public String getUtc_offset() {
            return utc_offset;
        }

        public void setUtc_offset(String utc_offset) {
            this.utc_offset = utc_offset;
        }

        @Override
        public String toString() {
            return "Location{" +
                    "name='" + name + '\'' +
                    ", country='" + country + '\'' +
                    ", region='" + region + '\'' +
                    ", lat='" + lat + '\'' +
                    ", lon='" + lon + '\'' +
                    ", timezone_id='" + timezone_id + '\'' +
                    ", localtime='" + localtime + '\'' +
                    ", localtime_epoch=" + localtime_epoch +
                    ", utc_offset='" + utc_offset + '\'' +
                    '}';
        }
    }

    /**
     * Classe Current respectant les conventions JavaBean
     */
    public static class Current implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private String observation_time;
        private int temperature;
        private int weather_code;
        private List<String> weather_icons;
        private List<String> weather_descriptions;
        private Astro astro;
        private AirQuality air_quality;
        private int wind_speed;
        private int wind_degree;
        private String wind_dir;
        private int pressure;
        private int precip;
        private int humidity;
        private int cloudcover;
        private int feelslike;
        private int uv_index;
        private int visibility;
        private String is_day;

        // Constructeur par défaut
        public Current() {
        }

        // Getters et Setters
        public String getObservation_time() {
            return observation_time;
        }

        public void setObservation_time(String observation_time) {
            this.observation_time = observation_time;
        }

        public int getTemperature() {
            return temperature;
        }

        public void setTemperature(int temperature) {
            this.temperature = temperature;
        }

        public int getWeather_code() {
            return weather_code;
        }

        public void setWeather_code(int weather_code) {
            this.weather_code = weather_code;
        }

        public List<String> getWeather_icons() {
            return weather_icons;
        }

        public void setWeather_icons(List<String> weather_icons) {
            this.weather_icons = weather_icons;
        }

        public List<String> getWeather_descriptions() {
            return weather_descriptions;
        }

        public void setWeather_descriptions(List<String> weather_descriptions) {
            this.weather_descriptions = weather_descriptions;
        }

        public Astro getAstro() {
            return astro;
        }

        public void setAstro(Astro astro) {
            this.astro = astro;
        }

        public AirQuality getAir_quality() {
            return air_quality;
        }

        public void setAir_quality(AirQuality air_quality) {
            this.air_quality = air_quality;
        }

        public int getWind_speed() {
            return wind_speed;
        }

        public void setWind_speed(int wind_speed) {
            this.wind_speed = wind_speed;
        }

        public int getWind_degree() {
            return wind_degree;
        }

        public void setWind_degree(int wind_degree) {
            this.wind_degree = wind_degree;
        }

        public String getWind_dir() {
            return wind_dir;
        }

        public void setWind_dir(String wind_dir) {
            this.wind_dir = wind_dir;
        }

        public int getPressure() {
            return pressure;
        }

        public void setPressure(int pressure) {
            this.pressure = pressure;
        }

        public int getPrecip() {
            return precip;
        }

        public void setPrecip(int precip) {
            this.precip = precip;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public int getCloudcover() {
            return cloudcover;
        }

        public void setCloudcover(int cloudcover) {
            this.cloudcover = cloudcover;
        }

        public int getFeelslike() {
            return feelslike;
        }

        public void setFeelslike(int feelslike) {
            this.feelslike = feelslike;
        }

        public int getUv_index() {
            return uv_index;
        }

        public void setUv_index(int uv_index) {
            this.uv_index = uv_index;
        }

        public int getVisibility() {
            return visibility;
        }

        public void setVisibility(int visibility) {
            this.visibility = visibility;
        }

        public String getIs_day() {
            return is_day;
        }

        public void setIs_day(String is_day) {
            this.is_day = is_day;
        }

        @Override
        public String toString() {
            return "Current{" +
                    "observation_time='" + observation_time + '\'' +
                    ", temperature=" + temperature +
                    ", weather_code=" + weather_code +
                    ", weather_icons=" + weather_icons +
                    ", weather_descriptions=" + weather_descriptions +
                    ", astro=" + astro +
                    ", air_quality=" + air_quality +
                    ", wind_speed=" + wind_speed +
                    ", wind_degree=" + wind_degree +
                    ", wind_dir='" + wind_dir + '\'' +
                    ", pressure=" + pressure +
                    ", precip=" + precip +
                    ", humidity=" + humidity +
                    ", cloudcover=" + cloudcover +
                    ", feelslike=" + feelslike +
                    ", uv_index=" + uv_index +
                    ", visibility=" + visibility +
                    ", is_day='" + is_day + '\'' +
                    '}';
        }
    }

    /**
     * Classe Astro respectant les conventions JavaBean
     */
    public static class Astro implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private String sunrise;
        private String sunset;
        private String moonrise;
        private String moonset;
        private String moon_phase;
        private int moon_illumination;

        // Constructeur par défaut
        public Astro() {
        }

        // Getters et Setters
        public String getSunrise() {
            return sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getSunset() {
            return sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }

        public String getMoonrise() {
            return moonrise;
        }

        public void setMoonrise(String moonrise) {
            this.moonrise = moonrise;
        }

        public String getMoonset() {
            return moonset;
        }

        public void setMoonset(String moonset) {
            this.moonset = moonset;
        }

        public String getMoon_phase() {
            return moon_phase;
        }

        public void setMoon_phase(String moon_phase) {
            this.moon_phase = moon_phase;
        }

        public int getMoon_illumination() {
            return moon_illumination;
        }

        public void setMoon_illumination(int moon_illumination) {
            this.moon_illumination = moon_illumination;
        }

        @Override
        public String toString() {
            return "Astro{" +
                    "sunrise='" + sunrise + '\'' +
                    ", sunset='" + sunset + '\'' +
                    ", moonrise='" + moonrise + '\'' +
                    ", moonset='" + moonset + '\'' +
                    ", moon_phase='" + moon_phase + '\'' +
                    ", moon_illumination=" + moon_illumination +
                    '}';
        }
    }

    /**
     * Classe AirQuality respectant les conventions JavaBean
     */
    public static class AirQuality implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private String co;
        private String no2;
        private String o3;
        private String so2;
        private String pm2_5;
        private String pm10;
        private String us_epa_index;
        private String gb_defra_index;

        // Constructeur par défaut
        public AirQuality() {
        }

        // Getters et Setters
        public String getCo() {
            return co;
        }

        public void setCo(String co) {
            this.co = co;
        }

        public String getNo2() {
            return no2;
        }

        public void setNo2(String no2) {
            this.no2 = no2;
        }

        public String getO3() {
            return o3;
        }

        public void setO3(String o3) {
            this.o3 = o3;
        }

        public String getSo2() {
            return so2;
        }

        public void setSo2(String so2) {
            this.so2 = so2;
        }

        public String getPm2_5() {
            return pm2_5;
        }

        public void setPm2_5(String pm2_5) {
            this.pm2_5 = pm2_5;
        }

        public String getPm10() {
            return pm10;
        }

        public void setPm10(String pm10) {
            this.pm10 = pm10;
        }

        public String getUs_epa_index() {
            return us_epa_index;
        }

        public void setUs_epa_index(String us_epa_index) {
            this.us_epa_index = us_epa_index;
        }

        public String getGb_defra_index() {
            return gb_defra_index;
        }

        public void setGb_defra_index(String gb_defra_index) {
            this.gb_defra_index = gb_defra_index;
        }

        @Override
        public String toString() {
            return "AirQuality{" +
                    "co='" + co + '\'' +
                    ", no2='" + no2 + '\'' +
                    ", o3='" + o3 + '\'' +
                    ", so2='" + so2 + '\'' +
                    ", pm2_5='" + pm2_5 + '\'' +
                    ", pm10='" + pm10 + '\'' +
                    ", us_epa_index='" + us_epa_index + '\'' +
                    ", gb_defra_index='" + gb_defra_index + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "WeatherResponse{" +
                "request=" + request +
                ", location=" + location +
                ", current=" + current +
                '}';
    }
} 