package com.trashgo.app.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 생성 - pkdgood
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PloggingData {
    public LocalDateTime ploggingDt;
    public String name;
    public List<Coordiante> latLngList = new ArrayList<>();
    public List<String> picList = new ArrayList<>();

    @Data
    @NoArgsConstructor
    public static class Coordiante {
        public double latitude;
        public double logitude;

        public Coordiante(double latitude, double longitude) {
            this.latitude = latitude;
            this.logitude = longitude;
        }
    }
}
