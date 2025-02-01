package com.example.navermap.Map;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;

import com.example.navermap.R;
import com.example.navermap.databinding.ActivityMap31Binding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraAnimation;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.MultipartPathOverlay;
import com.naver.maps.map.overlay.OverlayImage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Map3_1Activity extends AppCompatActivity implements OnMapReadyCallback {

    //map
    public NaverMap naverMap = null;
    public List<LatLng> polylines = null;
    public List<LatLng> polylines2 = null;
    public List<LatLng> polylines3 = null;
    public List<LatLng> polylines4 = null;
    public List<LatLng> polylines5 = null;
    public List<LatLng> polylines6 = null;
    public List<LatLng> polylines7 = null;
    public List<LatLng> polylines8 = null;
    public MultipartPathOverlay multipartPath = null;

    // BottomSheet layout variables
    private LinearLayout bottomSheetLayout;

    // BottomSheetBehavior
    public BottomSheetBehavior<LinearLayout> bottomSheetBehavior;

    // Data binding
    public ActivityMap31Binding binding = null;;
    public FloatingActionButton fab = null;
    //lat, lon
    private ArrayList<MapPoint> mapPoint = null;

    private  Marker[] markers = null;
    private Button[] places = null;

//    ---------------------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_map3_1);
        setPoints();
        initializePersistentBottomSheet();
        persistentBottomSheetEvent();

        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }

        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        fab = findViewById(R.id.fab);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        //Log.d(TAG, "onMapReady");
        this.naverMap = naverMap;

        CameraPosition cameraPosition = new CameraPosition(new LatLng(36.3504119, 127.3845475), 12);
        naverMap.setCameraPosition(cameraPosition);

        // API 호출을 위한 AsyncTask 실행
        new APICallTask().execute();
        new APICallTask2().execute();
        new APICallTask3().execute();
        new APICallTask4().execute();
        new APICallTask5().execute();
        new APICallTask6().execute();
        new APICallTask7().execute();
        new APICallTask8().execute();

        Insert_Marker();
    }

    private void Insert_Marker() {
        for(int i=0; i<mapPoint.size(); i++) {
            setMarker(markers[i], mapPoint.get(i).getLatitude(),mapPoint.get(i).getLongitude(), R.drawable.navermap_default_marker_icon_blue, 0, mapPoint.get(i).getName());
        }
    }


    private void setMarker(Marker marker, double lat, double lng, int resoureceID, int zIdeex, String text) {
        //마커 크기
        marker.setWidth(marker.SIZE_AUTO);
        marker.setHeight(marker.SIZE_AUTO);

        //원근감 표시
        marker.setIconPerspectiveEnabled(true);
        //아이콘
        marker.setIcon(OverlayImage.fromResource(resoureceID));
        //마커 투명도
        //marker.setAlpha(0.8f);

        //마커 위치
        marker.setPosition(new LatLng(lat, lng));
        marker.setZIndex(zIdeex);
        marker.setCaptionText(text);
        marker.setMap(naverMap);

    }

    private List<MapPoint> setPoints() {
        mapPoint = new ArrayList<MapPoint>();

        mapPoint.add(new MapPoint("체육재활원", 36.367796, 127.418476));
        mapPoint.add(new MapPoint("읍내동 주민센터", 36.376639, 127.420728));
        mapPoint.add(new MapPoint("읍내동 현대아파트 후문", 36.380016, 127.423395));
        mapPoint.add(new MapPoint("읍내동 현대아파트 정문", 36.379394, 127.42654));
        mapPoint.add(new MapPoint("대전 지방 국세청", 36.372199, 127.428346));
        mapPoint.add(new MapPoint("근로복지공단 대전병원 인근 버스정류장", 36.36883, 127.427502));
        mapPoint.add(new MapPoint("하나로 병원", 36.3617, 127.4315));
        mapPoint.add(new MapPoint("대전 세종 충남 본부 한전", 36.357554, 127.434708));
        mapPoint.add(new MapPoint("동부네거리", 36.351932, 127.44088));
        mapPoint.add(new MapPoint("대전 복합터미널", 36.349403, 127.437222));
        mapPoint.add(new MapPoint("경성코아(홍도 지하차도)", 36.345605, 127.429402));
        mapPoint.add(new MapPoint("홍도 신협", 36.3448966, 127.4208349));
        mapPoint.add(new MapPoint("중촌주공 아파트 2단지", 36.344049, 127.411456));
        mapPoint.add(new MapPoint("중촌주공 아파트 1단지", 36.339567, 127.408636));
        mapPoint.add(new MapPoint("대전 선병원", 36.336335, 127.410846));
        mapPoint.add(new MapPoint("대전 은행선화 행정복지센터", 36.33248, 127.416762));
        mapPoint.add(new MapPoint("대전 준법 지원센터", 36.328129, 127.420868));
        mapPoint.add(new MapPoint("흥국화재 글로벌 지점", 36.328016, 127.424363));
        mapPoint.add(new MapPoint("대우당 약국", 36.3298857, 127.4285489));
        mapPoint.add(new MapPoint("대전역(중고가전|중앙시장)", 36.329629, 127.433696));
        mapPoint.add(new MapPoint("삼성네거리", 36.3380531, 127.4296266));
        mapPoint.add(new MapPoint("한밭자이(아파트정류장)", 36.341259, 127.425179));
        mapPoint.add(new MapPoint("솔랑마을아파트(버스정류장)", 36.343412, 127.422462));
        mapPoint.add(new MapPoint("대덕구청", 36.3469091, 127.4161547));
        mapPoint.add(new MapPoint("호남철교", 36.354676, 127.415353));
        mapPoint.add(new MapPoint("오정네거리", 36.3585504, 127.415544));
        mapPoint.add(new MapPoint("체육재활원", 36.367796, 127.418476));

        markers = new Marker[mapPoint.size()];
        places = new Button[mapPoint.size()];

        for (int i = 0; i < mapPoint.size(); i++) {
            markers[i] = new Marker(); // Marker 클래스의 생성자를 호출하여 객체를 생성하고 배열에 할당합니다.
            int resourceId = getResources().getIdentifier("place" + (i+1), "id", getPackageName());

            if (resourceId != 0) {
                places[i] = findViewById(resourceId);
            }
        }
        return mapPoint;

    }

    //api Direction
    private class APICallTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            polylines = new ArrayList<>();

            try {
                // API 요청을 위한 URL 생성
                String start = String.valueOf(mapPoint.get(0).getLongitude()+","+mapPoint.get(0).getLatitude());
                String end = String.valueOf(mapPoint.get(3).getLongitude()+","+mapPoint.get(3).getLatitude());
                String waypoint="";
                for(int i=1; i<3; i++){
                    waypoint+=String.valueOf(mapPoint.get(i).getLongitude()+"," + mapPoint.get(i).getLatitude());
                    if(i!=2){
                        waypoint+=", ";
                    }
                }

                String apiUrl = String.format("https://naveropenapi.apigw.ntruss.com/map-direction-15/v1/driving?start=%S&goal=%S&option=traavoidtoll&waypoints=%S",start, end, waypoint);

//                    String apiUrl = String.format("https://naveropenapi.apigw.ntruss.com/map-direction-15/v1/driving?start=%S&goal=%S&option=trafast", start, end);

                    // URL 연결 설정
                    URL url = new URL(apiUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    // GET 메서드 설정
                    connection.setRequestMethod("GET");


                    // API 키 설정
                    connection.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "2pijo0g7vf");
                    connection.setRequestProperty("X-NCP-APIGW-API-KEY", "exP4SoaTeesL8o2e7xDtUEst91sAdi4KZlJxxKOu");

                    // 응답 코드 확인
                    int responseCode = connection.getResponseCode();

                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        // API 응답 데이터 읽기
                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        reader.close();
                        String jsonResponse = response.toString();
                        // JSON 데이터 파싱
                        JSONObject jsonObject = new JSONObject(jsonResponse);
                        JSONArray pathArray = jsonObject.getJSONObject("route")
                                .getJSONArray("traavoidtoll")
                                .getJSONObject(0)
                                .getJSONArray("path");

                        List<double[]> coordinatesList = new ArrayList<>();

                        for (int i = 0; i < pathArray.length(); i++) {
                            JSONArray coordinateArray = pathArray.getJSONArray(i);
                            double longitude = coordinateArray.getDouble(0);
                            double latitude = coordinateArray.getDouble(1);
                            double[] coordinates = {longitude, latitude};
                            coordinatesList.add(coordinates);
                        }
//                    String result = "";
//                    polylines = new ArrayList<>();

                        for (double[] coordinates : coordinatesList) {
                            LatLng point = new LatLng(coordinates[1], coordinates[0]);
                            polylines.add(point);
                        }
//                    return result;
                        return response.toString();
                    } else {
                        return "API 호출 실패: " + responseCode;
                    }
            }catch (Exception e) {
                e.printStackTrace();
                return "API 호출 실패: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // API 응답을 TextView에 표시
//            resulttext.setText(result);


        }
    }

    //api Direction
    private class APICallTask2 extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            polylines2 = new ArrayList<>();
            try {
                // API 요청을 위한 URL 생성
                String start = String.valueOf(mapPoint.get(3).getLongitude()+","+mapPoint.get(3).getLatitude());
                String end = String.valueOf(mapPoint.get(5).getLongitude()+","+mapPoint.get(5).getLatitude());
                String waypoint=String.valueOf(mapPoint.get(4).getLongitude()+","+mapPoint.get(4).getLatitude());

                String apiUrl = String.format("https://naveropenapi.apigw.ntruss.com/map-direction-15/v1/driving?start=%S&goal=%S&option=traavoidtoll&waypoints=%S",start, end, waypoint);

                // URL 연결 설정
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // GET 메서드 설정
                connection.setRequestMethod("GET");


                // API 키 설정
                connection.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "2pijo0g7vf");
                connection.setRequestProperty("X-NCP-APIGW-API-KEY", "exP4SoaTeesL8o2e7xDtUEst91sAdi4KZlJxxKOu");

                // 응답 코드 확인
                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // API 응답 데이터 읽기
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();
                    String jsonResponse = response.toString();
                    // JSON 데이터 파싱
                    JSONObject jsonObject = new JSONObject(jsonResponse);
                    JSONArray pathArray = jsonObject.getJSONObject("route")
                            .getJSONArray("traavoidtoll")
                            .getJSONObject(0)
                            .getJSONArray("path");

                    List<double[]> coordinatesList = new ArrayList<>();

                    for (int i = 0; i < pathArray.length(); i++) {
                        JSONArray coordinateArray = pathArray.getJSONArray(i);
                        double longitude = coordinateArray.getDouble(0);
                        double latitude = coordinateArray.getDouble(1);
                        double[] coordinates = {longitude, latitude};
                        coordinatesList.add(coordinates);
                    }
//                    String result = "";
//                    polylines = new ArrayList<>();

                    for (double[] coordinates : coordinatesList) {
                        LatLng point = new LatLng(coordinates[1], coordinates[0]);
                        polylines2.add(point);
                    }
//                    return result;
                    return response.toString();
                } else {
                    return "API 호출 실패: " + responseCode;
                }
            }catch (Exception e) {
                e.printStackTrace();
                return "API 호출 실패: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // API 응답을 TextView에 표시
//            resulttext.setText(result);
        }
    }

    //api Direction
    private class APICallTask3 extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            polylines3 = new ArrayList<>();

            try {
                // API 요청을 위한 URL 생성
                String start = String.valueOf(mapPoint.get(5).getLongitude()+","+mapPoint.get(5).getLatitude());
                String end = String.valueOf(mapPoint.get(8).getLongitude()+","+mapPoint.get(8).getLatitude());
                String waypoint="";
                for(int i=6; i<8; i++){
                    waypoint+=String.valueOf(mapPoint.get(i).getLongitude()+"," + mapPoint.get(i).getLatitude());
                    if(i!=7){
                        waypoint+=", ";
                    }
                }

                String apiUrl = String.format("https://naveropenapi.apigw.ntruss.com/map-direction-15/v1/driving?start=%S&goal=%S&option=traavoidtoll&waypoints=%S",start, end, waypoint);

                // URL 연결 설정
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // GET 메서드 설정
                connection.setRequestMethod("GET");


                // API 키 설정
                connection.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "2pijo0g7vf");
                connection.setRequestProperty("X-NCP-APIGW-API-KEY", "exP4SoaTeesL8o2e7xDtUEst91sAdi4KZlJxxKOu");

                // 응답 코드 확인
                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // API 응답 데이터 읽기
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();
                    String jsonResponse = response.toString();
                    // JSON 데이터 파싱
                    JSONObject jsonObject = new JSONObject(jsonResponse);
                    JSONArray pathArray = jsonObject.getJSONObject("route")
                            .getJSONArray("traavoidtoll")
                            .getJSONObject(0)
                            .getJSONArray("path");

                    List<double[]> coordinatesList = new ArrayList<>();

                    for (int i = 0; i < pathArray.length(); i++) {
                        JSONArray coordinateArray = pathArray.getJSONArray(i);
                        double longitude = coordinateArray.getDouble(0);
                        double latitude = coordinateArray.getDouble(1);
                        double[] coordinates = {longitude, latitude};
                        coordinatesList.add(coordinates);
                    }
//                    String result = "";
//                    polylines = new ArrayList<>();

                    for (double[] coordinates : coordinatesList) {
                        LatLng point = new LatLng(coordinates[1], coordinates[0]);
                        polylines3.add(point);
                    }
//                    return result;
                    return response.toString();
                } else {
                    return "API 호출 실패: " + responseCode;
                }
            }catch (Exception e) {
                e.printStackTrace();
                return "API 호출 실패: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // API 응답을 TextView에 표시
//            resulttext.setText(result);

        }
    }

    //api Direction
    private class APICallTask4 extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            polylines4 = new ArrayList<>();

            try {
                // API 요청을 위한 URL 생성
                String start = String.valueOf(mapPoint.get(8).getLongitude()+","+mapPoint.get(8).getLatitude());
                String end = String.valueOf(mapPoint.get(10).getLongitude()+","+mapPoint.get(10).getLatitude());
                String waypoint=String.valueOf(mapPoint.get(9).getLongitude()+","+mapPoint.get(9).getLatitude());

                String apiUrl = String.format("https://naveropenapi.apigw.ntruss.com/map-direction-15/v1/driving?start=%S&goal=%S&option=traavoidtoll&waypoints=%S",start, end, waypoint);

                // URL 연결 설정
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // GET 메서드 설정
                connection.setRequestMethod("GET");


                // API 키 설정
                connection.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "2pijo0g7vf");
                connection.setRequestProperty("X-NCP-APIGW-API-KEY", "exP4SoaTeesL8o2e7xDtUEst91sAdi4KZlJxxKOu");

                // 응답 코드 확인
                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // API 응답 데이터 읽기
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();
                    String jsonResponse = response.toString();
                    // JSON 데이터 파싱
                    JSONObject jsonObject = new JSONObject(jsonResponse);
                    JSONArray pathArray = jsonObject.getJSONObject("route")
                            .getJSONArray("traavoidtoll")
                            .getJSONObject(0)
                            .getJSONArray("path");

                    List<double[]> coordinatesList = new ArrayList<>();

                    for (int i = 0; i < pathArray.length(); i++) {
                        JSONArray coordinateArray = pathArray.getJSONArray(i);
                        double longitude = coordinateArray.getDouble(0);
                        double latitude = coordinateArray.getDouble(1);
                        double[] coordinates = {longitude, latitude};
                        coordinatesList.add(coordinates);
                    }
//                    String result = "";
//                    polylines = new ArrayList<>();

                    for (double[] coordinates : coordinatesList) {
                        LatLng point = new LatLng(coordinates[1], coordinates[0]);
                        polylines4.add(point);
                    }
//                    return result;
                    return response.toString();
                } else {
                    return "API 호출 실패: " + responseCode;
                }
            }catch (Exception e) {
                e.printStackTrace();
                return "API 호출 실패: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // API 응답을 TextView에 표시
//            resulttext.setText(result);


        }
    }

    //api Direction
    private class APICallTask5 extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            polylines5 = new ArrayList<>();

            try {
                // API 요청을 위한 URL 생성
                String start = String.valueOf(mapPoint.get(10).getLongitude()+","+mapPoint.get(10).getLatitude());
                String end = String.valueOf(mapPoint.get(13).getLongitude()+","+mapPoint.get(13).getLatitude());
                String waypoint="";
                for(int i=11; i<13; i++){
                    waypoint+=String.valueOf(mapPoint.get(i).getLongitude()+"," + mapPoint.get(i).getLatitude());
                    if(i!=12){
                        waypoint+=", ";
                    }
                }

                String apiUrl = String.format("https://naveropenapi.apigw.ntruss.com/map-direction-15/v1/driving?start=%S&goal=%S&option=traavoidtoll&waypoints=%S",start, end, waypoint);

                // URL 연결 설정
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // GET 메서드 설정
                connection.setRequestMethod("GET");


                // API 키 설정
                connection.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "2pijo0g7vf");
                connection.setRequestProperty("X-NCP-APIGW-API-KEY", "exP4SoaTeesL8o2e7xDtUEst91sAdi4KZlJxxKOu");

                // 응답 코드 확인
                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // API 응답 데이터 읽기
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();
                    String jsonResponse = response.toString();
                    // JSON 데이터 파싱
                    JSONObject jsonObject = new JSONObject(jsonResponse);
                    JSONArray pathArray = jsonObject.getJSONObject("route")
                            .getJSONArray("traavoidtoll")
                            .getJSONObject(0)
                            .getJSONArray("path");

                    List<double[]> coordinatesList = new ArrayList<>();

                    for (int i = 0; i < pathArray.length(); i++) {
                        JSONArray coordinateArray = pathArray.getJSONArray(i);
                        double longitude = coordinateArray.getDouble(0);
                        double latitude = coordinateArray.getDouble(1);
                        double[] coordinates = {longitude, latitude};
                        coordinatesList.add(coordinates);
                    }
//                    String result = "";
//                    polylines = new ArrayList<>();

                    for (double[] coordinates : coordinatesList) {
                        LatLng point = new LatLng(coordinates[1], coordinates[0]);
                        polylines5.add(point);
                    }
//                    return result;
                    return response.toString();
                } else {
                    return "API 호출 실패: " + responseCode;
                }
            }catch (Exception e) {
                e.printStackTrace();
                return "API 호출 실패: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // API 응답을 TextView에 표시
//            resulttext.setText(result);

        }
    }

    //api Direction
    private class APICallTask6 extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            polylines6 = new ArrayList<>();

            try {
                // API 요청을 위한 URL 생성
                String start = String.valueOf(mapPoint.get(13).getLongitude()+","+mapPoint.get(13).getLatitude());
                String end = String.valueOf(mapPoint.get(16).getLongitude()+","+mapPoint.get(16).getLatitude());
                String waypoint="";
                for(int i=14; i<16; i++){
                    waypoint+=String.valueOf(mapPoint.get(i).getLongitude()+"," + mapPoint.get(i).getLatitude());
                    if(i!=15){
                        waypoint+=", ";
                    }
                }

                String apiUrl = String.format("https://naveropenapi.apigw.ntruss.com/map-direction-15/v1/driving?start=%S&goal=%S&option=traavoidtoll&waypoints=%S",start, end, waypoint);

                // URL 연결 설정
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // GET 메서드 설정
                connection.setRequestMethod("GET");


                // API 키 설정
                connection.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "2pijo0g7vf");
                connection.setRequestProperty("X-NCP-APIGW-API-KEY", "exP4SoaTeesL8o2e7xDtUEst91sAdi4KZlJxxKOu");

                // 응답 코드 확인
                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // API 응답 데이터 읽기
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();
                    String jsonResponse = response.toString();
                    // JSON 데이터 파싱
                    JSONObject jsonObject = new JSONObject(jsonResponse);
                    JSONArray pathArray = jsonObject.getJSONObject("route")
                            .getJSONArray("traavoidtoll")
                            .getJSONObject(0)
                            .getJSONArray("path");

                    List<double[]> coordinatesList = new ArrayList<>();

                    for (int i = 0; i < pathArray.length(); i++) {
                        JSONArray coordinateArray = pathArray.getJSONArray(i);
                        double longitude = coordinateArray.getDouble(0);
                        double latitude = coordinateArray.getDouble(1);
                        double[] coordinates = {longitude, latitude};
                        coordinatesList.add(coordinates);
                    }
//                    String result = "";
//                    polylines = new ArrayList<>();

                    for (double[] coordinates : coordinatesList) {
                        LatLng point = new LatLng(coordinates[1], coordinates[0]);
                        polylines6.add(point);
                    }
//                    return result;
                    return response.toString();
                } else {
                    return "API 호출 실패: " + responseCode;
                }
            }catch (Exception e) {
                e.printStackTrace();
                return "API 호출 실패: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // API 응답을 TextView에 표시
//            resulttext.setText(result);

        }
    }

    //api Direction
    private class APICallTask7 extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            polylines7 = new ArrayList<>();

            try {
                // API 요청을 위한 URL 생성
                String start = String.valueOf(mapPoint.get(16).getLongitude()+","+mapPoint.get(16).getLatitude());
                String end = String.valueOf(mapPoint.get(19).getLongitude()+","+mapPoint.get(19).getLatitude());
                String waypoint="";
                for(int i=17; i<19; i++){
                    waypoint+=String.valueOf(mapPoint.get(i).getLongitude()+"," + mapPoint.get(i).getLatitude());
                    if(i!=18){
                        waypoint+=", ";
                    }
                }

                String apiUrl = String.format("https://naveropenapi.apigw.ntruss.com/map-direction-15/v1/driving?start=%S&goal=%S&option=traavoidtoll&waypoints=%S",start, end, waypoint);

                // URL 연결 설정
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // GET 메서드 설정
                connection.setRequestMethod("GET");


                // API 키 설정
                connection.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "2pijo0g7vf");
                connection.setRequestProperty("X-NCP-APIGW-API-KEY", "exP4SoaTeesL8o2e7xDtUEst91sAdi4KZlJxxKOu");

                // 응답 코드 확인
                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // API 응답 데이터 읽기
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();
                    String jsonResponse = response.toString();
                    // JSON 데이터 파싱
                    JSONObject jsonObject = new JSONObject(jsonResponse);
                    JSONArray pathArray = jsonObject.getJSONObject("route")
                            .getJSONArray("traavoidtoll")
                            .getJSONObject(0)
                            .getJSONArray("path");

                    List<double[]> coordinatesList = new ArrayList<>();

                    for (int i = 0; i < pathArray.length(); i++) {
                        JSONArray coordinateArray = pathArray.getJSONArray(i);
                        double longitude = coordinateArray.getDouble(0);
                        double latitude = coordinateArray.getDouble(1);
                        double[] coordinates = {longitude, latitude};
                        coordinatesList.add(coordinates);
                    }
//                    String result = "";
//                    polylines = new ArrayList<>();

                    for (double[] coordinates : coordinatesList) {
                        LatLng point = new LatLng(coordinates[1], coordinates[0]);
                        polylines7.add(point);
                    }
//                    return result;
                    return response.toString();
                } else {
                    return "API 호출 실패: " + responseCode;
                }
            }catch (Exception e) {
                e.printStackTrace();
                return "API 호출 실패: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // API 응답을 TextView에 표시
//            resulttext.setText(result);

        }
    }

    private class APICallTask8 extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            polylines8 = new ArrayList<>();

            try {
                // API 요청을 위한 URL 생성
                String start = String.valueOf(mapPoint.get(19).getLongitude()+","+mapPoint.get(19).getLatitude());
                String end = String.valueOf(mapPoint.get(26).getLongitude()+","+mapPoint.get(26).getLatitude());
                String waypoint="";
                for(int i=20; i<26; i++){
                    waypoint+=String.valueOf(mapPoint.get(i).getLongitude()+"," + mapPoint.get(i).getLatitude());
                    if(i!=25){
                        waypoint+=", ";
                    }
                }

                String apiUrl = String.format("https://naveropenapi.apigw.ntruss.com/map-direction-15/v1/driving?start=%S&goal=%S&option=traavoidtoll&waypoints=%S",start, end, waypoint);

                // URL 연결 설정
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // GET 메서드 설정
                connection.setRequestMethod("GET");


                // API 키 설정
                connection.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "2pijo0g7vf");
                connection.setRequestProperty("X-NCP-APIGW-API-KEY", "exP4SoaTeesL8o2e7xDtUEst91sAdi4KZlJxxKOu");

                // 응답 코드 확인
                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // API 응답 데이터 읽기
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();
                    String jsonResponse = response.toString();
                    // JSON 데이터 파싱
                    JSONObject jsonObject = new JSONObject(jsonResponse);
                    JSONArray pathArray = jsonObject.getJSONObject("route")
                            .getJSONArray("traavoidtoll")
                            .getJSONObject(0)
                            .getJSONArray("path");

                    List<double[]> coordinatesList = new ArrayList<>();

                    for (int i = 0; i < pathArray.length(); i++) {
                        JSONArray coordinateArray = pathArray.getJSONArray(i);
                        double longitude = coordinateArray.getDouble(0);
                        double latitude = coordinateArray.getDouble(1);
                        double[] coordinates = {longitude, latitude};
                        coordinatesList.add(coordinates);
                    }
//                    String result = "";
//                    polylines = new ArrayList<>();

                    for (double[] coordinates : coordinatesList) {
                        LatLng point = new LatLng(coordinates[1], coordinates[0]);
                        polylines8.add(point);
                    }
//                    return result;
                    return response.toString();
                } else {
                    return "API 호출 실패: " + responseCode;
                }
            }catch (Exception e) {
                e.printStackTrace();
                return "API 호출 실패: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // API 응답을 TextView에 표시
//            resulttext.setText(result);


            multipartPath = new MultipartPathOverlay();
            List<List<LatLng>> coordParts = Arrays.asList(polylines, polylines2, polylines3, polylines4, polylines5, polylines6, polylines7, polylines8);

            multipartPath.setCoordParts(coordParts);
            multipartPath.setColorParts(Arrays.asList(
                    new MultipartPathOverlay.ColorPart(
                            Color.RED, Color.RED, Color.RED, Color.RED),
                    new MultipartPathOverlay.ColorPart(
                            Color.RED, Color.RED, Color.RED, Color.RED),
                    new MultipartPathOverlay.ColorPart(
                            Color.RED, Color.RED, Color.RED, Color.RED),
                    new MultipartPathOverlay.ColorPart(
                            Color.RED, Color.RED, Color.RED, Color.RED),
                    new MultipartPathOverlay.ColorPart(
                            Color.RED, Color.RED, Color.RED, Color.RED),
                    new MultipartPathOverlay.ColorPart(
                            Color.RED, Color.RED, Color.RED, Color.RED),
                    new MultipartPathOverlay.ColorPart(
                            Color.RED, Color.RED, Color.RED, Color.RED),
                    new MultipartPathOverlay.ColorPart(
                            Color.RED, Color.RED, Color.RED, Color.RED)
            ));

            multipartPath.setMap(naverMap);
        }
    }


    // Initialize Persistent BottomSheet
    private void initializePersistentBottomSheet() {
        // Initialize BottomSheetBehavior with your layout
        bottomSheetLayout = findViewById(R.id.bottom_sheet_layout3_1);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);

        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                // Handle events based on the BottomSheet state
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.d("Map3_1Activity", "state: hidden");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.d("Map3_1Activity", "state: expanded");
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.d("Map3_1Activity", "state: collapsed");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.d("Map3_1Activity", "state: dragging");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.d("Map3_1Activity", "state: settling");
                        break;
                    case BottomSheetBehavior.STATE_HALF_EXPANDED:
                        Log.d("Map3_1Activity", "state: half expanded");
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                // Handle sliding events
            }
        });
    }

    // Handle events for buttons in the Persistent BottomSheet
    private void persistentBottomSheetEvent() {
        for (int i = 0; i < places.length; i++) {
            final int position = i;
            places[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CameraUpdate cameraUpdate = CameraUpdate.scrollAndZoomTo(
                            new LatLng(mapPoint.get(position).getLatitude(), mapPoint.get(position).getLongitude()), 17)
                            .animate(CameraAnimation.Fly, 3000);

                    naverMap.moveCamera(cameraUpdate);
                }
            });
        }


    }
}

