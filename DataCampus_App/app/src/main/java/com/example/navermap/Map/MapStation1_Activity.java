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
import com.example.navermap.databinding.ActivityBusstop1MapBinding;
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


public class MapStation1_Activity extends AppCompatActivity implements OnMapReadyCallback {

    //map
    public NaverMap naverMap = null;

    //lat, lon
    private ArrayList<MapPoint> mapPoint = null;

    private  Marker[] markers = null;
    private Button[] places = null;
    // BottomSheet layout variables
    private LinearLayout bottomSheetLayout = null;

    // BottomSheetBehavior
    public BottomSheetBehavior<LinearLayout> bottomSheetBehavior=null;

    // Data binding
    public ActivityBusstop1MapBinding binding = null;
    public FloatingActionButton fab = null;


//    ---------------------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_busstop1_map);
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

    private List<MapPoint> setPoints() {
        mapPoint = new ArrayList<MapPoint>();

        mapPoint.add(new MapPoint("SK브로드밴드", 36.35122244, 127.380457));
        mapPoint.add(new MapPoint("아이빌딩", 36.35138877, 127.3804453));
        mapPoint.add(new MapPoint("갤러리아백화점", 36.35260627, 127.3790876));
        mapPoint.add(new MapPoint("은하수네거리", 36.35073804, 127.3779528));
        mapPoint.add(new MapPoint("갤러리아타임월드", 36.35189638, 127.3770786));
        mapPoint.add(new MapPoint("파랑새네거리", 36.35314559, 127.3796365));
        mapPoint.add(new MapPoint("큰마을네거리", 36.34923079, 127.3762199));
        mapPoint.add(new MapPoint("탄방역 삼거리", 36.34740369, 127.3868779));
        mapPoint.add(new MapPoint("둔산2동주민센터", 36.35351946, 127.3808934));
        mapPoint.add(new MapPoint("큰마을아파트", 36.34736352, 127.3760034));
        mapPoint.add(new MapPoint("목련아파트", 36.35033428, 127.3900647));
        mapPoint.add(new MapPoint("탄방역.대전고용센터", 36.34687853, 127.3855047));
        mapPoint.add(new MapPoint("이마트", 36.3559648, 127.3786121));
        mapPoint.add(new MapPoint("시청,교육청", 36.35140178, 127.3835145));
        mapPoint.add(new MapPoint("한우리아파트", 36.34684127, 127.388684));
        mapPoint.add(new MapPoint("시청역", 36.35118764, 127.3886553));
        mapPoint.add(new MapPoint("시청역6번출구", 36.35138812, 127.3882783));
        mapPoint.add(new MapPoint("한방역,고용지원센터", 36.3466245, 127.3856361));
        mapPoint.add(new MapPoint("대전광역시청", 36.35117168, 127.3850934));
        mapPoint.add(new MapPoint("시청", 36.35172266, 127.3852063));
        mapPoint.add(new MapPoint("종점", 36.35252685, 127.3844853));
        mapPoint.add(new MapPoint("둔산여고", 36.35365031, 127.3742194));
        mapPoint.add(new MapPoint("서부농협본점", 36.34242421, 127.3875029));
        mapPoint.add(new MapPoint("탄방역종점", 36.34547914, 127.3852561));
        mapPoint.add(new MapPoint("대전상공회의소", 36.34627124, 127.3789309));
        mapPoint.add(new MapPoint("둔산초등학교", 36.34853046, 127.3830921));
        mapPoint.add(new MapPoint("탄방역1번출구", 36.34537431, 127.3844389));
        mapPoint.add(new MapPoint("한가람아파트", 36.34887859, 127.3922039));
        mapPoint.add(new MapPoint("향촌아파트", 36.35511322, 127.3744301));
        mapPoint.add(new MapPoint("탄방네거리", 36.34224471, 127.3863002));
        mapPoint.add(new MapPoint("샛별아파트", 36.35991861, 127.3760387));
        mapPoint.add(new MapPoint("둔산경찰서", 36.35900567, 127.3792809));
        mapPoint.add(new MapPoint("법원,검찰청", 36.35441096, 127.3903903));
        mapPoint.add(new MapPoint("개나리아파트", 36.34337355, 127.3830734));
        mapPoint.add(new MapPoint("무지개아파트", 36.35912795, 127.3744121));
        mapPoint.add(new MapPoint("을지대학병원", 36.35519205, 127.3831225));
        mapPoint.add(new MapPoint("한아름아파트", 36.36235143, 127.3758128));
        mapPoint.add(new MapPoint("선사유적지", 36.36184223, 127.3792959));
        mapPoint.add(new MapPoint("백합네거리", 36.36218057, 127.3771216));
        mapPoint.add(new MapPoint("예지중고등학교", 36.34016642, 127.3874971));
        mapPoint.add(new MapPoint("충남고교", 36.34912337, 127.3930746));
        mapPoint.add(new MapPoint("성룡초등학교", 36.36163523, 127.3744231));
        mapPoint.add(new MapPoint("정부대전청사서문", 36.36366138, 127.3797401));
        mapPoint.add(new MapPoint("정부청사역", 36.35791519, 127.3818264));
        mapPoint.add(new MapPoint("정부대전청사남문", 36.35994274, 127.3812646));
        mapPoint.add(new MapPoint("느리울13단지", 36.29630678, 127.3392979));
        mapPoint.add(new MapPoint("크로바아파트", 36.35137375, 127.393156));
        mapPoint.add(new MapPoint("누리아파트후문", 36.35915742, 127.3742416));
        mapPoint.add(new MapPoint("관저건양대병원시외버스정류장", 36.3022728, 127.3390235));
        mapPoint.add(new MapPoint("백운초등학교", 36.33971287, 127.3867424));



//        mapPoint.add(new MapPoint("SK브로드밴드", 36.35122244, 127.380457));
//        mapPoint.add(new MapPoint("아이빌딩", 36.35138877, 127.3804453));
//        mapPoint.add(new MapPoint("갤러리아백화점", 36.35260627, 127.3790876));
//        mapPoint.add(new MapPoint("은하수네거리", 36.35073804, 127.3779528));
//        mapPoint.add(new MapPoint("갤러리아타임월드", 36.35189638, 127.3770786));
//        mapPoint.add(new MapPoint("파랑새네거리", 36.35314559, 127.3796365));
//        mapPoint.add(new MapPoint("큰마을네거리", 36.34923079, 127.3762199));
//        mapPoint.add(new MapPoint("탄방역 삼거리", 36.34740369, 127.3868779));
//        mapPoint.add(new MapPoint("둔산2동주민센터", 36.35351946, 127.3808934));
//        mapPoint.add(new MapPoint("큰마을아파트", 36.34736352, 127.3760034));
//        mapPoint.add(new MapPoint("목련아파트", 36.35033428, 127.3900647));
//        mapPoint.add(new MapPoint("탄방역.대전고용센터", 36.34687853, 127.3855047));
//        mapPoint.add(new MapPoint("이마트", 36.3559648, 127.3786121));
//        mapPoint.add(new MapPoint("시청,교육청", 36.35140178, 127.3835145));
//        mapPoint.add(new MapPoint("한우리아파트", 36.34684127, 127.388684));
//        mapPoint.add(new MapPoint("시청역", 36.35118764, 127.3886553));
//        mapPoint.add(new MapPoint("시청역6번출구", 36.35138812, 127.3882783));
//        mapPoint.add(new MapPoint("한방역,고용지원센터", 36.3466245, 127.3856361));
//        mapPoint.add(new MapPoint("대전광역시청", 36.35117168, 127.3850934));
//        mapPoint.add(new MapPoint("시청", 36.35172266, 127.3852063));
//        mapPoint.add(new MapPoint("종점", 36.35252685, 127.3844853));
//        mapPoint.add(new MapPoint("둔산여고", 36.35365031, 127.3742194));
//        mapPoint.add(new MapPoint("서부농협본점", 36.34242421, 127.3875029));
//        mapPoint.add(new MapPoint("탄방역종점", 36.34547914, 127.3852561));
//        mapPoint.add(new MapPoint("대전상공회의소", 36.34627124, 127.3789309));
//        mapPoint.add(new MapPoint("둔산초등학교", 36.34853046, 127.3830921));
//        mapPoint.add(new MapPoint("탄방역1번출구", 36.34537431, 127.3844389));
//        mapPoint.add(new MapPoint("한가람아파트", 36.34887859, 127.3922039));
//        mapPoint.add(new MapPoint("향촌아파트", 36.35511322, 127.3744301));
//        mapPoint.add(new MapPoint("탄방네거리", 36.34224471, 127.3863002));
//        mapPoint.add(new MapPoint("샛별아파트", 36.35991861, 127.3760387));
//        mapPoint.add(new MapPoint("둔산경찰서", 36.35900567, 127.3792809));
//        mapPoint.add(new MapPoint("법원,검찰청", 36.35441096, 127.3903903));
//        mapPoint.add(new MapPoint("개나리아파트", 36.34337355, 127.3830734));
//        mapPoint.add(new MapPoint("무지개아파트", 36.35912795, 127.3744121));
//        mapPoint.add(new MapPoint("을지대학병원", 36.35519205, 127.3831225));
//        mapPoint.add(new MapPoint("한아름아파트", 36.36235143, 127.3758128));
//        mapPoint.add(new MapPoint("선사유적지", 36.36184223, 127.3792959));
//        mapPoint.add(new MapPoint("백합네거리", 36.36218057, 127.3771216));
//        mapPoint.add(new MapPoint("예지중고등학교", 36.34016642, 127.3874971));
//        mapPoint.add(new MapPoint("충남고교", 36.34912337, 127.3930746));
//        mapPoint.add(new MapPoint("성룡초등학교", 36.36163523, 127.3744231));
//        mapPoint.add(new MapPoint("정부대전청사서문", 36.36366138, 127.3797401));
//        mapPoint.add(new MapPoint("정부청사역", 36.35791519, 127.3818264));
//        mapPoint.add(new MapPoint("정부대전청사남문", 36.35994274, 127.3812646));
//        mapPoint.add(new MapPoint("느리울13단지", 36.29630678, 127.3392979));
//        mapPoint.add(new MapPoint("크로바아파트", 36.35137375, 127.393156));
//        mapPoint.add(new MapPoint("누리아파트후문", 36.35915742, 127.3742416));
//        mapPoint.add(new MapPoint("관저건양대병원시외버스정류장", 36.3022728, 127.3390235));
//        mapPoint.add(new MapPoint("백운초등학교", 36.33971287, 127.3867424));
//        mapPoint.add(new MapPoint("롯데백화점", 36.34008387, 127.3908746));
//        mapPoint.add(new MapPoint("건양대병원네거리버스정류장", 36.30241251, 127.3396434));
//        mapPoint.add(new MapPoint("신선마을아파트버스정류장", 36.30011045, 127.3347032));
//        mapPoint.add(new MapPoint("느리울아파트12단지", 36.2983702, 127.3404162));
//        mapPoint.add(new MapPoint("관저네거리버스정류장", 36.30173274, 127.3341266));
//        mapPoint.add(new MapPoint("녹원아파트", 36.34607276, 127.3818311));
//        mapPoint.add(new MapPoint("무궁화아파트", 36.36315323, 127.3793119));
//        mapPoint.add(new MapPoint("관저2동행정복지센터", 36.29976924, 127.335007));
//        mapPoint.add(new MapPoint("법원.검찰청", 36.35591807, 127.3872979));
//        mapPoint.add(new MapPoint("선암초교네거리", 36.29620868, 127.3357408));
//        mapPoint.add(new MapPoint("도마1동행정복지센터", 36.31549548, 127.3791557));
//        mapPoint.add(new MapPoint("도마시장", 36.31478184, 127.3835038));
//        mapPoint.add(new MapPoint("관저지하차도버스정류장", 36.30175283, 127.3365744));
//        mapPoint.add(new MapPoint("도마e편한세상아파트", 36.31361571, 127.3815904));
//        mapPoint.add(new MapPoint("도마네거리", 36.31375244, 127.3812455));
//        mapPoint.add(new MapPoint("갈마2동주민센터/충청투데이", 36.34579903, 127.3747831));
//        mapPoint.add(new MapPoint("용문역5번출구", 36.33920747, 127.3924162));
//        mapPoint.add(new MapPoint("유승기업사", 36.34358798, 127.381058));
//        mapPoint.add(new MapPoint("신원상가", 36.31173249, 127.3767456));
//        mapPoint.add(new MapPoint("원양주고4단지버스정류장", 36.30452013, 127.3384809));
//        mapPoint.add(new MapPoint("건양대병원네거리", 36.30293129, 127.3436202));
//        mapPoint.add(new MapPoint("대자연아파트버스정류장", 36.30474886, 127.3385241));
//        mapPoint.add(new MapPoint("문정초등학교", 36.35043164, 127.3953439));
//        mapPoint.add(new MapPoint("건양병원네거리", 36.30281299, 127.3413462));
//        mapPoint.add(new MapPoint("괴정네거리", 36.30267158, 127.3346457));
//        mapPoint.add(new MapPoint("탄방중학교", 36.35025031, 127.395106));
//        mapPoint.add(new MapPoint("봉우중학교", 36.2990058, 127.331986));
//        mapPoint.add(new MapPoint("서구청", 36.35593498, 127.3839847));
//        mapPoint.add(new MapPoint("구봉마을육교", 36.29885035, 127.3320167));
//        mapPoint.add(new MapPoint("배재대학교", 36.32174657, 127.3701384));
//        mapPoint.add(new MapPoint("변동중학교", 36.32281971, 127.3738113));
//        mapPoint.add(new MapPoint("경남아파트", 36.32055394, 127.3705777));
//        mapPoint.add(new MapPoint("봉우재네거리", 36.30322849, 127.3351344));
//        mapPoint.add(new MapPoint("대전지방조달청", 36.32003582, 127.3705582));
//        mapPoint.add(new MapPoint("대자연마을아파트버스정류장", 36.30693036, 127.3369512));
//        mapPoint.add(new MapPoint("둔산전자타운", 36.3398905, 127.3951155));
//        mapPoint.add(new MapPoint("공작아파트", 36.34822746, 127.3950959));
//        mapPoint.add(new MapPoint("탄방동주민센터", 36.3480363, 127.3953345));
//        mapPoint.add(new MapPoint("한민시장", 36.3323438, 127.3830387));
//        mapPoint.add(new MapPoint("청솔아파트", 36.35135636, 127.3983544));
//        mapPoint.add(new MapPoint("진달래아파트", 36.35817864, 127.3662912));
//        mapPoint.add(new MapPoint("귀빈장네거리", 36.32143519, 127.3736194));
//        mapPoint.add(new MapPoint("맑은아침아파트", 36.33038677, 127.3830488));
//        mapPoint.add(new MapPoint("도마", 36.31155514, 127.3836074));
//        mapPoint.add(new MapPoint("남선공원종합체육관", 36.34886153, 127.39836));
//        mapPoint.add(new MapPoint("도마중학교", 36.31988261, 127.3736583));
//        mapPoint.add(new MapPoint("월평중학교", 36.35790024, 127.3663263));
//        mapPoint.add(new MapPoint("둥지아파트정문", 36.3596996, 127.3925913));
//        mapPoint.add(new MapPoint("국화아파트", 36.35418352, 127.3953962));
//        mapPoint.add(new MapPoint("하나로아파트", 36.35991438, 127.3662319));
//        mapPoint.add(new MapPoint("관저중학교버스정류장", 36.3053651, 127.3404976));
//        mapPoint.add(new MapPoint("구농도원네거리", 36.31759072, 127.3783602));
//        mapPoint.add(new MapPoint("사학연금회관", 36.3590041, 127.3901355));
//        mapPoint.add(new MapPoint("둥지아파트", 36.35783768, 127.3913036));
//        mapPoint.add(new MapPoint("구봉마을8단지", 36.29781619, 127.3297258));
//        mapPoint.add(new MapPoint("꿈나무아파트", 36.35983931, 127.3915963));
//        mapPoint.add(new MapPoint("괴정동주민센터", 36.33602704, 127.3820411));
//        mapPoint.add(new MapPoint("대자연마을아파트후문버스정류장", 36.30766537, 127.3391142));
//        mapPoint.add(new MapPoint("상수도사업본부", 36.34653036, 127.3685147));
//        mapPoint.add(new MapPoint("전원아파트", 36.3597865, 127.3651925));
//        mapPoint.add(new MapPoint("갈마육교", 36.35136464, 127.3718875));
//        mapPoint.add(new MapPoint("구봉마을7단지", 36.29771743, 127.329556));
//        mapPoint.add(new MapPoint("월평주공아파트", 36.36217892, 127.3693597));
//        mapPoint.add(new MapPoint("한마음동산", 36.34424219, 127.3686302));
//        mapPoint.add(new MapPoint("서대전고등학교", 36.35926896, 127.3685022));
//        mapPoint.add(new MapPoint("촹실타운아파트", 36.35990632, 127.3711359));
//        mapPoint.add(new MapPoint("한국과학기술원셔틀버스승차장", 36.35858098, 127.3631303));
//        mapPoint.add(new MapPoint("한밭고,갈마중입구", 36.34672721, 127.3693456));
//        mapPoint.add(new MapPoint("제일고등학교", 36.31502041, 127.3734417));
//        mapPoint.add(new MapPoint("변동근린공원", 36.32527419, 127.3738014));
//        mapPoint.add(new MapPoint("용화5길입구", 36.31931979, 127.3757665));
//        mapPoint.add(new MapPoint("정부대전청사", 36.36139466, 127.3901195));
//        mapPoint.add(new MapPoint("샘머리아파트", 36.36156409, 127.3903917));
//        mapPoint.add(new MapPoint("원앙마을아파트버스정류장", 36.30802556, 127.3388806));
//        mapPoint.add(new MapPoint("구봉마을9단지", 36.29613649, 127.3315427));
//        mapPoint.add(new MapPoint("대전일보사", 36.3535948, 127.3607634));
//        mapPoint.add(new MapPoint("둥지네거리", 36.35967989, 127.3891966));
//        mapPoint.add(new MapPoint("갈마역", 36.35789865, 127.3720364));
//        mapPoint.add(new MapPoint("누리아파트", 36.35972898, 127.3719552));
//        mapPoint.add(new MapPoint("배재시장", 36.31710744, 127.3735379));
//        mapPoint.add(new MapPoint("서부소방서", 36.31024339, 127.3723751));
//        mapPoint.add(new MapPoint("한마루아파트", 36.35573591, 127.3925832));
//        mapPoint.add(new MapPoint("월평역", 36.35849734, 127.3616065));
//        mapPoint.add(new MapPoint("금동초등학교", 36.29682823, 127.3274906));
//        mapPoint.add(new MapPoint("KT서대전지사입구", 36.33848357, 127.3851806));
//        mapPoint.add(new MapPoint("둔원초등학교", 36.34409521, 127.3749652));
//        mapPoint.add(new MapPoint("구봉마을5단지", 36.30029339, 127.3271555));
//        mapPoint.add(new MapPoint("삼육중학교입구", 36.31180435, 127.3744375));
//        mapPoint.add(new MapPoint("복음양지아파트", 36.31642167, 127.3711572));
//        mapPoint.add(new MapPoint("샘머리공원", 36.35755588, 127.3933898));
//        mapPoint.add(new MapPoint("갈마중학교입구", 36.34942566, 127.368154));
//        mapPoint.add(new MapPoint("동산아파트입구", 36.34965433, 127.3693744));

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

    //Initialize Persistent BottomSheet
    private void initializePersistentBottomSheet() {
        // Initialize BottomSheetBehavior with your layout
        bottomSheetLayout = findViewById(R.id.bottom_sheet_busstop1);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);

        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                // Handle events based on the BottomSheet state
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.d("MapStation1_Activity", "state: hidden");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.d("MapStation1_Activity", "state: expanded");
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.d("MapStation1_Activity", "state: collapsed");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.d("MapStation1_Activity", "state: dragging");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.d("MapStation1_Activity", "state: settling");
                        break;
                    case BottomSheetBehavior.STATE_HALF_EXPANDED:
                        Log.d("MapStation1_Activity", "state: half expanded");
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

