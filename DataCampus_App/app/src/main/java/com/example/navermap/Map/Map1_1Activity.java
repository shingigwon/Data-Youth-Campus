package com.example.navermap.Map;
import com.example.navermap.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

//binding
import androidx.databinding.DataBindingUtil;
import com.example.navermap.databinding.ActivityMap11Binding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

//naver
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

//json
import org.json.JSONArray;
import org.json.JSONObject;



public class Map1_1Activity extends AppCompatActivity implements OnMapReadyCallback {

    //map
    public NaverMap naverMap = null;
    public List<LatLng> polylines = null;
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
    public ActivityMap11Binding binding = null;;
    public FloatingActionButton fab = null;
    //lat, lon
    private ArrayList<MapPoint> mapPoint = null;
    private  Marker[] markers = null;
    private Button[] places = null;

//    ---------------------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_map1_1);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_map1_1);

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

    private void setPoints() {
        mapPoint = new ArrayList<MapPoint>();

        mapPoint.add(new MapPoint("보라매삼거리 태원중국집 앞", 36.3461564,127.3816889));
        mapPoint.add(new MapPoint("한신아파트 앞", 36.34358,127.381024));
        mapPoint.add(new MapPoint("백운초 정류장", 36.339761,127.38678));
        mapPoint.add(new MapPoint("괴정주유소 앞", 36.3376875,127.3855625));
        mapPoint.add(new MapPoint("나르메아파트 2단지 117동 앞", 36.332418,127.385423));
        mapPoint.add(new MapPoint("맑은아침아파트 106동", 36.3310812,127.3837149));
        mapPoint.add(new MapPoint("변동오거리 이화산부인과 앞", 36.3282479, 127.3806871));
        mapPoint.add(new MapPoint("삼정빌딩 한내과 건너편",36.3225478,127.3778393));
        mapPoint.add(new MapPoint("농도원네거리 서림주유소",36.319661,127.37756));
        mapPoint.add(new MapPoint("농협 도마동지점",36.3137736,127.379274));
        mapPoint.add(new MapPoint("서부교육청",36.3130274,127.3789099));
        mapPoint.add(new MapPoint("서부소방서 앞",36.310261,127.372446));
        mapPoint.add(new MapPoint("양지맨션 앞 슈퍼",36.3177399,127.369792));
        mapPoint.add(new MapPoint("경남아파트 6동 앞",36.3210196,127.3707795));
        mapPoint.add(new MapPoint("여성회관 앞",36.3247153,127.3697515));
        mapPoint.add(new MapPoint("기쁜 우리션교회",36.3267448,127.3727025));
        mapPoint.add(new MapPoint("아이사랑 어린이집 앞",36.3295073,127.3756927));
        mapPoint.add(new MapPoint("빙빙축산 정육점 앞",36.3325626,127.3749546));
        mapPoint.add(new MapPoint("린나이 대리점 앞",36.3547637,127.3840975));
        mapPoint.add(new MapPoint("경성큰마을 앞 버스 승강장",36.344,127.3749));
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
    private class APICallTask2 extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            polylines2 = new ArrayList<>();

            try {
                // API 요청을 위한 URL 생성
                String start = String.valueOf(mapPoint.get(2).getLongitude()+","+mapPoint.get(2).getLatitude());
                String end = String.valueOf(mapPoint.get(11).getLongitude()+","+mapPoint.get(11).getLatitude());
                String waypoint="";
                for(int i=3; i<11; i++){
                    waypoint+=String.valueOf(mapPoint.get(i).getLongitude()+"," + mapPoint.get(i).getLatitude());
                    if(i!=10){
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
                String start = String.valueOf(mapPoint.get(14).getLongitude()+","+mapPoint.get(14).getLatitude());
                String end = String.valueOf(mapPoint.get(17).getLongitude()+","+mapPoint.get(17).getLatitude());
                String waypoint="";
                for(int i=15; i<17; i++) {
                    waypoint += String.valueOf(mapPoint.get(i).getLongitude() + "," + mapPoint.get(i).getLatitude());
                    if (i != 16) {
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
                for(int i=18; i<20; i++){
                    waypoint+=String.valueOf(mapPoint.get(i).getLongitude()+"," + mapPoint.get(i).getLatitude());
                    if(i!=19){
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


            multipartPath = new MultipartPathOverlay();
            List<List<LatLng>> coordParts = Arrays.asList(polylines, polylines2, polylines3, polylines4, polylines5, polylines6);
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
                            Color.RED, Color.RED, Color.RED, Color.RED)
            ));

            multipartPath.setMap(naverMap);
        }
    }


    // Initialize Persistent BottomSheet
    private void initializePersistentBottomSheet() {
        // Initialize BottomSheetBehavior with your layout
        bottomSheetLayout = findViewById(R.id.bottom_sheet_layout1_1);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);

        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                // Handle events based on the BottomSheet state
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.d("Map1_1Activity", "state: hidden");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.d("Map1_1Activity", "state: expanded");
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.d("Map1_1Activity", "state: collapsed");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.d("Map1_1Activity", "state: dragging");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.d("Map1_1Activity", "state: settling");
                        break;
                    case BottomSheetBehavior.STATE_HALF_EXPANDED:
                        Log.d("Map1_1Activity", "state: half expanded");
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

