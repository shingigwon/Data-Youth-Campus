package com.example.navermap.Map;

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
import com.example.navermap.databinding.ActivityBusstop2MapBinding;
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
import com.naver.maps.map.overlay.OverlayImage;

import java.util.ArrayList;

import java.util.List;


public class MapStation2_Activity extends AppCompatActivity implements OnMapReadyCallback {

    //map
    public NaverMap naverMap = null;

    //lat, lon
    private ArrayList<MapPoint> mapPoint = null;

    private  Marker[] markers = null;
    private Button[] places = null;
    // BottomSheet layout variables
    private LinearLayout bottomSheetLayout;

    // BottomSheetBehavior
    public BottomSheetBehavior<LinearLayout> bottomSheetBehavior;

    // Data binding
    public ActivityBusstop2MapBinding binding = null;;
    public FloatingActionButton fab = null;


//    ---------------------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_busstop2_map);
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

        CameraPosition cameraPosition = new CameraPosition(new LatLng(36.3504119, 127.3845475), 14);
        naverMap.setCameraPosition(cameraPosition);

        Insert_Marker();
    }

    private List<MapPoint> setPoints() {
        mapPoint = new ArrayList<MapPoint>();

        mapPoint.add(new MapPoint("서대전역네거리", 36.32200454, 127.4104203));
        mapPoint.add(new MapPoint("서대전네거리역2번출구", 36.32150932, 127.4096894));
        mapPoint.add(new MapPoint("서대전네거리역", 36.32119759, 127.4138541));
        mapPoint.add(new MapPoint("중구청", 36.32580468, 127.4210518));
        mapPoint.add(new MapPoint("유천네거리", 36.32090833, 127.3966494));
        mapPoint.add(new MapPoint("태평초등학교", 36.32172817, 127.394377));
        mapPoint.add(new MapPoint("대전중부경찰서", 36.32752558, 127.4230542));
        mapPoint.add(new MapPoint("삼성생명", 36.3280133, 127.4237962));
        mapPoint.add(new MapPoint("대전고등학교", 36.32292676, 127.42427));
        mapPoint.add(new MapPoint("삼부아파트", 36.32334009, 127.3937537));
        mapPoint.add(new MapPoint("대고오거리", 36.32373512, 127.4225827));
        mapPoint.add(new MapPoint("성모오거리", 36.32305454, 127.4222113));
        mapPoint.add(new MapPoint("교원연합회", 36.323458, 127.4210985));
        mapPoint.add(new MapPoint("태평시장", 36.3248413, 127.3952487));
        mapPoint.add(new MapPoint("대흥네거리", 36.3239145, 127.4272639));
        mapPoint.add(new MapPoint("태평1동주민센터", 36.32662776, 127.3973781));
        mapPoint.add(new MapPoint("중앙로네거리", 36.32792511, 127.4241315));
        mapPoint.add(new MapPoint("유천시장", 36.3180725, 127.3965413));
        mapPoint.add(new MapPoint("성모병원", 36.32339458, 127.4185091));
        mapPoint.add(new MapPoint("쌍용예가아파트", 36.32273578, 127.3981286));
        mapPoint.add(new MapPoint("유천동서대전농협", 36.31787269, 127.3949138));
        mapPoint.add(new MapPoint("문화동하우스토리", 36.31986928, 127.4158953));
        mapPoint.add(new MapPoint("충남대학교병원", 36.31794665, 127.41391));
        mapPoint.add(new MapPoint("중앙로역6번출구", 36.32837908, 127.4247866));
        mapPoint.add(new MapPoint("중앙로역4번출구", 36.32815054, 127.4247338));
        mapPoint.add(new MapPoint("중구청역", 36.32428593, 127.4187611));
        mapPoint.add(new MapPoint("우천동현대아파트", 36.31878422, 127.3983396));
        mapPoint.add(new MapPoint("대전충남병무청", 36.32271319, 127.4147111));
        mapPoint.add(new MapPoint("태평오거리", 36.32660239, 127.3928134));
        mapPoint.add(new MapPoint("충대병원네거리", 36.31889734, 127.4166773));
        mapPoint.add(new MapPoint("유천2동주민센터", 36.31660444, 127.3979908));
        mapPoint.add(new MapPoint("센트럴파크아파트", 36.3171734, 127.4072328));
        mapPoint.add(new MapPoint("버드내네거리", 36.31706267, 127.3926539));
        mapPoint.add(new MapPoint("서대전역", 36.32351713, 127.4045093));
        mapPoint.add(new MapPoint("버드내아파트", 36.31634413, 127.3867747));
        mapPoint.add(new MapPoint("서대전육교", 36.31899888, 127.4003078));
        mapPoint.add(new MapPoint("대흥동성당", 36.32692782, 127.4267547));
        mapPoint.add(new MapPoint("산성네거리", 36.30339153, 127.3861129));
        mapPoint.add(new MapPoint("대전광역시노인복지관", 36.31992915, 127.4200138));
        mapPoint.add(new MapPoint("산성시장", 36.3061864, 127.3868533));
        mapPoint.add(new MapPoint("한밭가든아파트", 36.30462043, 127.3846439));
        mapPoint.add(new MapPoint("충남지방경찰청", 36.32878785, 127.4206676));
        mapPoint.add(new MapPoint("테미삼거리", 36.31837172, 127.41868));
        mapPoint.add(new MapPoint("산성동행정복지센터", 36.30607704, 127.3866183));
        mapPoint.add(new MapPoint("대전중학교", 36.32016179, 127.4242145));
        mapPoint.add(new MapPoint("중앙로역", 36.3301483, 127.42387));
        mapPoint.add(new MapPoint("버드내2단지", 36.3218476, 127.3901421));
        mapPoint.add(new MapPoint("삼부/새롬아파트", 36.3220831, 127.3900242));
        mapPoint.add(new MapPoint("대자연아파트", 36.31530272, 127.417118));
        mapPoint.add(new MapPoint("목동네거리", 36.33549552, 127.41217));
        mapPoint.add(new MapPoint("평촌네거리", 36.33588342, 127.4152822));
        mapPoint.add(new MapPoint("중앙중고등학교", 36.33915677, 127.4129561));
        mapPoint.add(new MapPoint("중촌네거리", 36.33589867, 127.4147761));
        mapPoint.add(new MapPoint("선병원", 36.33745832, 127.4096216));
        mapPoint.add(new MapPoint("서대전역입구", 36.32497493, 127.4005842));
        mapPoint.add(new MapPoint("중천동주민센터", 36.33863718, 127.4133243));
        mapPoint.add(new MapPoint("문화주공삼거리", 36.3108248, 127.4015234));
        mapPoint.add(new MapPoint("머티네거리", 36.31145374, 127.3887889));
        mapPoint.add(new MapPoint("푸른뫼아파트", 36.32841017, 127.3989823));
        mapPoint.add(new MapPoint("센트럴파크", 36.31633632, 127.4094683));
        mapPoint.add(new MapPoint("문화동주공아파트", 36.31046626, 127.3996289));
        mapPoint.add(new MapPoint("문화동.주공3단지후문", 36.31074909, 127.4014743));
        mapPoint.add(new MapPoint("대전국제통상고", 36.31327925, 127.40844));
        mapPoint.add(new MapPoint("충남기계공고", 36.3123945, 127.4021859));
        mapPoint.add(new MapPoint("중구보건소", 36.30847804, 127.3958759));


        markers = new Marker[mapPoint.size()];
        places = new Button[mapPoint.size()];

        for (int i = 0; i < mapPoint.size(); i++) {
            markers[i] = new Marker(); // Marker 클래스의 생성자를 호출하여 객체를 생성하고 배열에 할당합니다.
            int resourceId = getResources().getIdentifier("busstop" + (i+1), "id", getPackageName());

            if (resourceId != 0) {
                places[i] = findViewById(resourceId);
            }
        }
        return mapPoint;

    }




    private void Insert_Marker() {
        for(int i=0; i<mapPoint.size(); i++) {
            setMarker(markers[i], mapPoint.get(i).getLatitude(),mapPoint.get(i).getLongitude(), R.drawable.d_marker, 0, mapPoint.get(i).getName());
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



    //Initialize Persistent BottomSheet
    private void initializePersistentBottomSheet() {
        // Initialize BottomSheetBehavior with your layout
        bottomSheetLayout = findViewById(R.id.bottom_sheet_busstop2);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);

        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                // Handle events based on the BottomSheet state
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.d("MapStation2_Activity", "state: hidden");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.d("MapStation2_Activity", "state: expanded");
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.d("MapStation2_Activity", "state: collapsed");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.d("MapStation2_Activity", "state: dragging");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.d("MapStation2_Activity", "state: settling");
                        break;
                    case BottomSheetBehavior.STATE_HALF_EXPANDED:
                        Log.d("MapStation2_Activity", "state: half expanded");
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

