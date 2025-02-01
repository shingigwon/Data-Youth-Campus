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
import com.example.navermap.databinding.ActivityBusstop3MapBinding;
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


public class MapStation3_Activity extends AppCompatActivity implements OnMapReadyCallback {

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
    public ActivityBusstop3MapBinding binding = null;;
    public FloatingActionButton fab = null;


//    ---------------------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_busstop3_map);
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


        CameraPosition cameraPosition = new CameraPosition(new LatLng(36.3285736, 127.4050512), 14);
        naverMap.setCameraPosition(cameraPosition);

        Insert_Marker();
    }

    private List<MapPoint> setPoints() {
        mapPoint = new ArrayList<MapPoint>();

        mapPoint.add(new MapPoint("SK브로드밴드", 36.35122244, 127.380457));
        mapPoint.add(new MapPoint("아이빌딩", 36.35138877, 127.3804453));
        mapPoint.add(new MapPoint("갤러리아백화점", 36.35260627, 127.3790876));
        mapPoint.add(new MapPoint("은하수네거리", 36.35073804, 127.3779528));
        mapPoint.add(new MapPoint("갤러리아타임월드", 36.35189638, 127.3770786));
        mapPoint.add(new MapPoint("파랑새네거리", 36.35314559, 127.3796365));
        mapPoint.add(new MapPoint("둔산2동주민센터", 36.35351946, 127.3808934));
        mapPoint.add(new MapPoint("큰마을네거리", 36.34923079, 127.3762199));
        mapPoint.add(new MapPoint("이마트", 36.3559648, 127.3786121));
        mapPoint.add(new MapPoint("시청,교육청", 36.35140178, 127.3835145));
        mapPoint.add(new MapPoint("대전광역시청", 36.35117168, 127.3850934));
        mapPoint.add(new MapPoint("목련아파트", 36.35033428, 127.3900647));
        mapPoint.add(new MapPoint("시청역", 36.35118764, 127.3886553));
        mapPoint.add(new MapPoint("시청역6번출구", 36.35138812, 127.3882783));
        mapPoint.add(new MapPoint("시청", 36.35172266, 127.3852063));
        mapPoint.add(new MapPoint("종점", 36.35252685, 127.3844853));
        mapPoint.add(new MapPoint("둔산여고", 36.3531709, 127.3743873));
        mapPoint.add(new MapPoint("향촌아파트", 36.35511322, 127.3744301));
        mapPoint.add(new MapPoint("샛별아파트", 36.35991861, 127.3760387));
        mapPoint.add(new MapPoint("둔산경찰서", 36.35900567, 127.3792809));
        mapPoint.add(new MapPoint("법원,검찰청", 36.35441096, 127.3903903));
        mapPoint.add(new MapPoint("무지개아파트", 36.35912795, 127.3744121));
        mapPoint.add(new MapPoint("을지대학병원", 36.35519205, 127.3831225));
        mapPoint.add(new MapPoint("한아름아파트", 36.36235143, 127.3758128));
        mapPoint.add(new MapPoint("대전상공회의소", 36.34627124, 127.3789309));
        mapPoint.add(new MapPoint("백합네거리", 36.36218057, 127.3771216));
        mapPoint.add(new MapPoint("선사유적지", 36.36184223, 127.3792959));
        mapPoint.add(new MapPoint("정부대전청사서문", 36.36366138, 127.3797401));
        mapPoint.add(new MapPoint("성룡초등학교", 36.36163523, 127.3744231));
        mapPoint.add(new MapPoint("느리울13단지", 36.29630678, 127.3392979));
        mapPoint.add(new MapPoint("정부대전청사남문", 36.35994274, 127.3812646));
        mapPoint.add(new MapPoint("정부청사역", 36.35791519, 127.3818264));
        mapPoint.add(new MapPoint("중구청", 36.32580468, 127.4210518));
        mapPoint.add(new MapPoint("누리아파트후문", 36.35915742, 127.3742416));
        mapPoint.add(new MapPoint("관저건양대병원시외버스정류장", 36.3022728, 127.3390235));
        mapPoint.add(new MapPoint("대전고등학교", 36.32292676, 127.42427));
        mapPoint.add(new MapPoint("건양대병원네거리버스정류장", 36.30241251, 127.3396434));
        mapPoint.add(new MapPoint("느리울아파트12단지", 36.2983702, 127.3404162));
        mapPoint.add(new MapPoint("신선마을아파트버스정류장", 36.30011045, 127.3347032));
        mapPoint.add(new MapPoint("관저네거리버스정류장", 36.30173274, 127.3341266));
        mapPoint.add(new MapPoint("무궁화아파트", 36.36315323, 127.3793119));
        mapPoint.add(new MapPoint("관저2동행정복지센터", 36.29976924, 127.335007));
        mapPoint.add(new MapPoint("법원.검찰청", 36.35591807, 127.3872979));
        mapPoint.add(new MapPoint("대고오거리", 36.32373512, 127.4225827));
        mapPoint.add(new MapPoint("성모오거리", 36.32305454, 127.4222113));
        mapPoint.add(new MapPoint("유천시장", 36.3180725, 127.3965413));
        mapPoint.add(new MapPoint("교원연합회", 36.323458, 127.4210985));
        mapPoint.add(new MapPoint("선암초교네거리", 36.29620868, 127.3357408));
        mapPoint.add(new MapPoint("도마1동행정복지센터", 36.31549548, 127.3791557));
        mapPoint.add(new MapPoint("유천네거리", 36.32090833, 127.3966494));



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
        bottomSheetLayout = findViewById(R.id.bottom_sheet_busstop3);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);

        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                // Handle events based on the BottomSheet state
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.d("MapStation3_Activity", "state: hidden");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.d("MapStation3_Activity", "state: expanded");
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.d("MapStation3_Activity", "state: collapsed");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.d("MapStation3_Activity", "state: dragging");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.d("MapStation3_Activity", "state: settling");
                        break;
                    case BottomSheetBehavior.STATE_HALF_EXPANDED:
                        Log.d("MapStation3_Activity", "state: half expanded");
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

