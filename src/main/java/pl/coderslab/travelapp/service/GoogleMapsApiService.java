package pl.coderslab.travelapp.service;
import com.google.maps.*;
import com.google.maps.model.*;
import org.springframework.stereotype.Service;
import pl.coderslab.travelapp.entity.Travel;
@Service
public class GoogleMapsApiService {
    private final String API_KEY = "AIzaSyAHFMgh1GXKYJsB8xyebY7uE-9I_LXNOcU";

    public double getDistance(String origin, String destination){
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(API_KEY)
                .build();

        try {
            DistanceMatrix distanceMatrix = DistanceMatrixApi.newRequest(context)
                    .origins(origin)
                    .destinations(destination)
                    .await();

            return distanceMatrix.rows[0].elements[0].distance.inMeters / 1000.0;
        } catch (Exception e) {
            return -1;
        }
    }

    public double countWaterInLiters(Travel travel){
        double waterInLiters;
        double avgSpeed = travel.getVehicleSpeed();
        double distance = travel.getDistance();
        if(avgSpeed<=20){
            waterInLiters = distance/50;
        }
        else if (avgSpeed<=60){
            waterInLiters = distance/100;
        }
        else {
            waterInLiters=distance/300;
        }
        return waterInLiters;
    }

    public LatLng getLatLngFromAddress(String address) {
        try {
            // Inicjalizacja kontekstu API z użyciem klucza API Google Maps
            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey(API_KEY)
                    .build();

            // Wykonanie zapytania geokodowania
            GeocodingResult[] results = GeocodingApi.geocode(context, address).await();

            // Sprawdzenie, czy uzyskano wyniki
            if (results != null && results.length > 0) {
                // Pobranie współrzędnych geograficznych pierwszego wyniku
                LatLng location = results[0].geometry.location;
                return location;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getMapUrl(String address){
        StringBuilder sb= new StringBuilder();
        sb.append("https://maps.googleapis.com/maps/api/staticmap?center=");
        sb.append(getLatLngFromAddress(address));
        sb.append("&zoom=14&size=600x600&maptype=satellite");
        sb.append("&key="+API_KEY);
        return sb.toString();
    }
}
