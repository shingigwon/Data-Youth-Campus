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
import com.example.navermap.databinding.ActivityMap13Binding;
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


public class Map1_3Activity extends AppCompatActivity implements OnMapReadyCallback {

    //map
    public NaverMap naverMap = null;
    public List<LatLng> polylines = null;
    public List<LatLng> polylines1_1 = null;
    public List<LatLng> polylines2 = null;
    public List<LatLng> polylines3 = null;
    public List<LatLng> polylines4 = null;
    public List<LatLng> polylines5 = null;
    public List<LatLng> polylines6 = null;
    public MultipartPathOverlay multipartPath = null;

    // BottomSheet layout variables
    private LinearLayout bottomSheetLayout;

    // BottomSheetBehavior
    public BottomSheetBehavior<LinearLayout> bottomSheetBehavior;

    // Data binding
    public ActivityMap13Binding binding = null;;
    public FloatingActionButton fab = null;
    //lat, lon
    private ArrayList<MapPoint> mapPoint = null;

    private  Marker[] markers = null;
    private Button[] places = null;


//    ---------------------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_map1_3);
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
//        new APICallTask().execute();
//        new APICallTask1_1().execute();
//        new APICallTask2().execute();
//        new APICallTask3().execute();
//        new APICallTask4().execute();
//        new APICallTask5().execute();
//        new APICallTask6().execute();

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

        mapPoint.add(new MapPoint("우리안과 앞", 36.3525628,127.3787144));
        mapPoint.add(new MapPoint("갈마2동 주민센터 앞", 36.346608,127.375193));
        mapPoint.add(new MapPoint("내동 코오롱 육교 밑", 36.334497,127.369886));
        mapPoint.add(new MapPoint("롯데 코오롱 상가 앞", 36.337775, 127.366697));
        mapPoint.add(new MapPoint("갈마동 공무원아파트 203동 앞", 36.342305, 127.367116));
        mapPoint.add(new MapPoint("대전광역시 산림조합 앞", 36.346986,127.369452));
        mapPoint.add(new MapPoint("백년예식장 앞", 36.3482997, 127.383134));
        mapPoint.add(new MapPoint("갈마농협",36.3539195,127.3680889));
        mapPoint.add(new MapPoint("이마트 앞 주유소",36.357439,127.381149));
        mapPoint.add(new MapPoint("하나로아파트 상가 앞",36.3592441,127.3772421));
        mapPoint.add(new MapPoint("서대전고등학교 앞",36.359206,127.368532));
        mapPoint.add(new MapPoint("월평주공아파트 1단지 상가 앞",36.36214,127.369299));
        mapPoint.add(new MapPoint("성룡초등학교 앞",36.361353,127.374175));
        mapPoint.add(new MapPoint("무지개아파트 107동 버스승강장 앞",36.359687,127.376027));
        mapPoint.add(new MapPoint("둥지아파트 정문 앞",36.359664,127.392663));
        mapPoint.add(new MapPoint("서원초등학교 정문 앞",36.358982,127.395064));
        mapPoint.add(new MapPoint("국화아파트 303동 옆앞 공원",36.346523,127.3924529));
        mapPoint.add(new MapPoint("탄방중학교 정문 앞",36.350393,127.395052));
        mapPoint.add(new MapPoint("한양공작아파트 정문",36.346523,127.3924529));
        mapPoint.add(new MapPoint("한가람아파트 5동 앞",36.348883,127.39229));
        mapPoint.add(new MapPoint("한우리아파트 건너편",36.346882,127.388707));
        mapPoint.add(new MapPoint("서구건강체련관",36.3547637,127.3840975));

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
                String end = String.valueOf(mapPoint.get(2).getLongitude()+","+mapPoint.get(2).getLatitude());
                String waypoint=String.valueOf(mapPoint.get(1).getLongitude()+","+mapPoint.get(1).getLatitude());

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
    private class APICallTask1_1 extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            polylines1_1 = new ArrayList<>();
            try {
                // API 요청을 위한 URL 생성
                String start = String.valueOf(mapPoint.get(2).getLongitude()+","+mapPoint.get(2).getLatitude());
                String end = String.valueOf(mapPoint.get(3).getLongitude()+","+mapPoint.get(3).getLatitude());
                String apiUrl = String.format("https://naveropenapi.apigw.ntruss.com/map-direction-15/v1/driving?start=%S&goal=%S&option=traavoidtoll",start, end);

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
                        polylines1_1.add(point);
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
                String start = String.valueOf(mapPoint.get(0).getLongitude()+","+mapPoint.get(0).getLatitude());
                String end = String.valueOf(mapPoint.get(11).getLongitude()+","+mapPoint.get(11).getLatitude());
                String waypoint="";
                for(int i=0; i<12; i++){
                    waypoint+=String.valueOf(mapPoint.get(i).getLongitude()+"," + mapPoint.get(i).getLatitude());
                    if(i!=11){
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
                String start = String.valueOf(mapPoint.get(11).getLongitude()+","+mapPoint.get(11).getLatitude());
                String end = String.valueOf(mapPoint.get(12).getLongitude()+","+mapPoint.get(12).getLatitude());
                String apiUrl = String.format("https://naveropenapi.apigw.ntruss.com/map-direction-15/v1/driving?start=%S&goal=%S&option=traavoidtoll",start, end);

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
                String start = String.valueOf(mapPoint.get(12).getLongitude()+","+mapPoint.get(12).getLatitude());
                String end = String.valueOf(mapPoint.get(14).getLongitude()+","+mapPoint.get(14).getLatitude());
                String waypoint = String.valueOf(mapPoint.get(13).getLongitude()+","+mapPoint.get(13).getLatitude());

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
                String start = String.valueOf(mapPoint.get(11).getLongitude()+","+mapPoint.get(11).getLatitude());
                String end = String.valueOf(mapPoint.get(21).getLongitude()+","+mapPoint.get(21).getLatitude());
                String waypoint="";
                for(int i=11; i<22; i++) {
                    waypoint += String.valueOf(mapPoint.get(i).getLongitude() + "," + mapPoint.get(i).getLatitude());
                    if (i != 21) {
                        waypoint += ", ";
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

            multipartPath = new MultipartPathOverlay();
            List<List<LatLng>> coordParts = Arrays.asList(polylines2, polylines5);
            multipartPath.setCoordParts(coordParts);
            multipartPath.setColorParts(Arrays.asList(
                    new MultipartPathOverlay.ColorPart(
                            Color.RED, Color.RED, Color.RED, Color.RED),
                    new MultipartPathOverlay.ColorPart(
                            Color.RED, Color.RED, Color.RED, Color.RED)
            ));

            multipartPath.setMap(naverMap);

        }
    }

    //api Direction
    private class APICallTask6 extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            polylines6 = new ArrayList<>();

            try {
                // API 요청을 위한 URL 생성
                String start = String.valueOf(mapPoint.get(17).getLongitude()+","+mapPoint.get(17).getLatitude());
                String end = String.valueOf(mapPoint.get(20).getLongitude()+","+mapPoint.get(20).getLatitude());
                String waypoint="";
                for(int i=17; i<21; i++){
                    waypoint+=String.valueOf(mapPoint.get(i).getLongitude()+"," + mapPoint.get(i).getLatitude());
                    if(i!=20){
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


//            multipartPath = new MultipartPathOverlay();
//            List<List<LatLng>> coordParts = Arrays.asList(polylines, polylines1_1, polylines2, polylines3, polylines4, polylines5, polylines6);
//            multipartPath.setCoordParts(coordParts);
//            multipartPath.setColorParts(Arrays.asList(
//                    new MultipartPathOverlay.ColorPart(
//                            Color.RED, Color.RED, Color.RED, Color.RED),
//                    new MultipartPathOverlay.ColorPart(
//                            Color.RED, Color.RED, Color.RED, Color.RED),
//                    new MultipartPathOverlay.ColorPart(
//                            Color.RED, Color.RED, Color.RED, Color.RED),
//                    new MultipartPathOverlay.ColorPart(
//                            Color.RED, Color.RED, Color.RED, Color.RED),
//                    new MultipartPathOverlay.ColorPart(
//                            Color.RED, Color.RED, Color.RED, Color.RED),
//                    new MultipartPathOverlay.ColorPart(
//                            Color.RED, Color.RED, Color.RED, Color.RED),
//                    new MultipartPathOverlay.ColorPart(
//                            Color.RED, Color.RED, Color.RED, Color.RED)
//            ));
//
//            multipartPath.setMap(naverMap);
        }
    }


    // Initialize Persistent BottomSheet
    private void initializePersistentBottomSheet() {
        // Initialize BottomSheetBehavior with your layout
        bottomSheetLayout = findViewById(R.id.bottom_sheet_layout1_3);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);

        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                // Handle events based on the BottomSheet state
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.d("Map1_3Activity", "state: hidden");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.d("Map1_3Activity", "state: expanded");
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.d("Map1_3Activity", "state: collapsed");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.d("Map1_3Activity", "state: dragging");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.d("Map1_3Activity", "state: settling");
                        break;
                    case BottomSheetBehavior.STATE_HALF_EXPANDED:
                        Log.d("Map1_3Activity", "state: half expanded");
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

