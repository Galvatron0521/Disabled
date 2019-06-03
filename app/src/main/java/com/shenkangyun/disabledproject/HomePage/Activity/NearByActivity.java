package com.shenkangyun.disabledproject.HomePage.Activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.google.gson.Gson;
import com.shenkangyun.disabledproject.BaseFolder.Base;
import com.shenkangyun.disabledproject.BeanFolder.MechanismBean;
import com.shenkangyun.disabledproject.HomePage.overlayutil.DrivingRouteOverlay;
import com.shenkangyun.disabledproject.HomePage.overlayutil.TransitRouteOverlay;
import com.shenkangyun.disabledproject.HomePage.overlayutil.WalkingRouteOverlay;
import com.shenkangyun.disabledproject.R;
import com.shenkangyun.disabledproject.UtilsFolder.FuncUtil;
import com.shenkangyun.disabledproject.UtilsFolder.GsonCallBack;
import com.shenkangyun.disabledproject.UtilsFolder.LocationService;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NearByActivity extends AppCompatActivity implements View.OnClickListener, TabLayout.OnTabSelectedListener {

    private final int SDK_PERMISSION_REQUEST = 127;
    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.mMapView)
    MapView mMapView;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    private int size;
    private LatLng point;
    private BaiduMap mBaiduMap;
    private String permissionInfo;
    private LocationService locService;
    private List<MechanismBean.DataBean.ListBean> listBeans;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private String city = "泰安";
    private LatLng ll;
    private PlanNode stNode;
    private PlanNode enNode;
    private GeoCoder geocode;
    private RoutePlanSearch mSearch;

    private String name;
    private String phone;
    private String callPhone;
    private double xAxis;
    private double yAxis;
    private String responsibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.white);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("附近医疗");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        getPermissions();
        initView();
        initLocation();
        initData();
    }

    private void initView() {
        mBaiduMap = mMapView.getMap();
        listBeans = new ArrayList<>();
        geocode = GeoCoder.newInstance();
        mSearch = RoutePlanSearch.newInstance();
        tabLayout.addTab(tabLayout.newTab().setText("公交"));
        tabLayout.addTab(tabLayout.newTab().setText("驾车"));
        tabLayout.addTab(tabLayout.newTab().setText("步行"));
        tabLayout.addOnTabSelectedListener(this);
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(15));
        locService = new LocationService(getApplication());
        LocationClientOption mOption = locService.getDefaultLocationClientOption();
        mOption.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
        mOption.setCoorType("bd09ll");
        locService.setLocationOption(mOption);
        locService.registerListener(listener);
        locService.start();
    }

    private void initLocation() {
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "organizeList")
                .addParams("data", new organizeList(Base.getMD5Str(), Base.getTimeSpan(), "1", "0", "100").toJson())
                .build().execute(new GsonCallBack<MechanismBean>() {
            @Override
            public void onSuccess(MechanismBean response) throws JSONException {
                size = response.getData().getList().size();
                for (int i = 0; i < size; i++) {
                    MechanismBean.DataBean.ListBean listBean = new MechanismBean.DataBean.ListBean();
                    name = response.getData().getList().get(i).getName();
                    phone = response.getData().getList().get(i).getPhone();
                    xAxis = response.getData().getList().get(i).getXAxis();
                    yAxis = response.getData().getList().get(i).getYAxis();
                    responsibility = response.getData().getList().get(i).getResponsibilityName();

                    LatLng latLng = new LatLng(yAxis, xAxis);
                    BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_openmap_mark);
                    OverlayOptions option = new MarkerOptions().position(latLng)
                            .icon(bitmap).zIndex(9).draggable(true);
                    Marker marker = (Marker) mBaiduMap.addOverlay(option);
                    Bundle bundle = new Bundle();
                    bundle.putString("name", name);
                    bundle.putString("phone", phone);
                    bundle.putString("responsibility", responsibility);
                    marker.setExtraInfo(bundle);

                    listBean.setName(name);
                    listBean.setPhone(phone);
                    listBean.setXAxis(xAxis);
                    listBean.setYAxis(yAxis);
                    listBean.setResponsibilityName(responsibility);
                    listBeans.add(listBean);
                }
            }

            @Override
            public void onError(Exception e) {
            }
        });
    }

    private void initData() {
        final View popupView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_map_item, null);
        final TextView text_name = popupView.findViewById(R.id.text_name);
        final TextView text_tel = popupView.findViewById(R.id.text_tel);
        final TextView text_contacts = popupView.findViewById(R.id.text_contacts);
        final Button btn_go = popupView.findViewById(R.id.btn_go);
        //添加marker点击事件的监听
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                //从marker中获取经纬度的信息来转化成屏幕的坐标
                String name = marker.getExtraInfo().getString("name");
                callPhone = marker.getExtraInfo().getString("phone");
                String responsibility = marker.getExtraInfo().getString("responsibility");
                ll = marker.getPosition();
                Point point = mBaiduMap.getProjection().toScreenLocation(ll);
                LatLng locInfo = mBaiduMap.getProjection().fromScreenLocation(point);

                text_name.setText(name);
                text_tel.setText(callPhone);
                text_contacts.setText(responsibility);
                text_tel.setOnClickListener(NearByActivity.this);
                btn_go.setOnClickListener(NearByActivity.this);
                //信息窗口
                final InfoWindow mInfoWindow = new InfoWindow(popupView, locInfo, -47);
                if (popupView.isShown()) {
                    mBaiduMap.hideInfoWindow();  //隐藏
                } else {
                    mBaiduMap.showInfoWindow(mInfoWindow);//显示
                }
                return true;
            }
        });

        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (popupView.isShown()) {
                    mBaiduMap.hideInfoWindow();  //隐藏
                }
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });
    }


    /***
     * 定位结果回调，在此方法中处理定位结果
     */
    BDAbstractLocationListener listener = new BDAbstractLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (location != null && (location.getLocType() == 161 || location.getLocType() == 66)) {
                Message locMsg = locHander.obtainMessage();
                Bundle locData = new Bundle();
                locData.putParcelable("loc", location);
                locMsg.setData(locData);
                locHander.sendMessage(locMsg);
            }
        }

    };

    /***
     * 接收定位结果消息，并显示在地图上
     */
    private Handler locHander = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            try {
                BDLocation location = msg.getData().getParcelable("loc");
                if (location != null) {
                    point = new LatLng(location.getLatitude(), location.getLongitude());
                    // 构建Marker图标
                    getLocationInfo(point);
                    BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_openmap_focuse_mark); // 推算结果
                    // 构建MarkerOption，用于在地图上添加Marker
                    OverlayOptions option = new MarkerOptions().position(point).icon(bitmap)
                            .zIndex(9).draggable(true);
                    // 在地图上添加Marker，并显示
                    Marker marker = (Marker) mBaiduMap.addOverlay(option);
                    Bundle bundle = new Bundle();
                    bundle.putString("name", "当前位置");
                    bundle.putString("phone", "");
                    bundle.putString("responsibility", "");
                    marker.setExtraInfo(bundle);
                    mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(point));
                    mBaiduMap.setMyLocationEnabled(false);
                    if (locService.isStart()) {
                        locService.stop();
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    };

    private void getLocationInfo(LatLng point) {
        geocode.setOnGetGeoCodeResultListener(Listener);
        geocode.reverseGeoCode(new ReverseGeoCodeOption().location(point));
    }

    @OnClick(R.id.Location)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Location:
                if (mBaiduMap != null) {
                    mBaiduMap.clear();
                    initIcon();
                    mBaiduMap.setMyLocationEnabled(true);
                    locService.start();
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_go:
                mBaiduMap.clear();
                initIcon();
                tabLayout.setVisibility(View.VISIBLE);
                getTransitRoute(ll);
                break;
            case R.id.text_tel:
                if (ContextCompat.checkSelfPermission(NearByActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // 没有获得授权，申请授权
                    if (ActivityCompat.shouldShowRequestPermissionRationale(NearByActivity.this,
                            Manifest.permission.CALL_PHONE)) {
                        Toast.makeText(NearByActivity.this, "请授权！", Toast.LENGTH_LONG).show();

                        // 帮跳转到该应用的设置界面，让用户手动授权
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else {
                        // 不需要解释为何需要该权限，直接请求授权
                        ActivityCompat.requestPermissions(NearByActivity.this,
                                new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
                    }
                } else {
                    // 已经获得授权，可以打电话
                    CallPhone();
                }
                break;
        }
    }

    private void CallPhone() {
        if (TextUtils.isEmpty(callPhone)) {
            // 提醒用户
            // 注意：在这个匿名内部类中如果用this则表示是View.OnClickListener类的对象，
            // 所以必须用MainActivity.this来指定上下文环境。
            Toast.makeText(NearByActivity.this, "号码不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            // 拨号：激活系统的拨号组件
            Intent intent = new Intent(); // 意图对象：动作 + 数据
            intent.setAction(Intent.ACTION_DIAL); // 设置动作
            Uri data = Uri.parse("tel:" + callPhone); // 设置数据
            intent.setData(data);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    private void getTransitRoute(LatLng ll) {
        mSearch.setOnGetRoutePlanResultListener(planResultListener);
        // 起点与终点
        stNode = PlanNode.withLocation(point);
        enNode = PlanNode.withLocation(ll);
        // 步行路线规划
        mSearch.transitSearch(new TransitRoutePlanOption().from(
                stNode).city(city).to(enNode));
    }

    OnGetRoutePlanResultListener planResultListener = new OnGetRoutePlanResultListener() {
        @Override
        public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
            // 获取步行线路规划结果
            if (walkingRouteResult == null || walkingRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
                Toast.makeText(NearByActivity.this, "抱歉，未找到结果",
                        Toast.LENGTH_SHORT).show();
            }
            if (walkingRouteResult.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                walkingRouteResult.getSuggestAddrInfo();
                return;
            }
            if (walkingRouteResult.error == SearchResult.ERRORNO.NO_ERROR) {
                WalkingRouteOverlay overlay = new WalkingRouteOverlay(
                        mBaiduMap);
                mBaiduMap.setOnMarkerClickListener(overlay);
                overlay.setData(walkingRouteResult.getRouteLines().get(0));
                overlay.addToMap();
                overlay.zoomToSpan();
            }
        }

        @Override
        public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {
            // 获取公交换乘路径规划结果
            if (transitRouteResult == null || transitRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
                Toast.makeText(NearByActivity.this, "抱歉，未找到结果",
                        Toast.LENGTH_SHORT).show();
            }
            if (transitRouteResult.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                //起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                transitRouteResult.getSuggestAddrInfo();
                return;
            }
            if (transitRouteResult.error == SearchResult.ERRORNO.NO_ERROR) {
                TransitRouteOverlay overlay = new TransitRouteOverlay(mBaiduMap);
                mBaiduMap.setOnMarkerClickListener(overlay);
                overlay.setData(transitRouteResult.getRouteLines().get(0));
                overlay.addToMap();
                overlay.zoomToSpan();
                //Toast.makeText(GuideActivity.this,"点击图标会有指示哦～",
                //        Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
            // 获取公交换乘路径规划结果
            if (drivingRouteResult == null || drivingRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
                Toast.makeText(NearByActivity.this, "抱歉，未找到结果",
                        Toast.LENGTH_SHORT).show();
            }
            if (drivingRouteResult.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                //起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                drivingRouteResult.getSuggestAddrInfo();
                return;
            }
            if (drivingRouteResult.error == SearchResult.ERRORNO.NO_ERROR) {
                DrivingRouteOverlay overlay = new DrivingRouteOverlay(mBaiduMap);
                mBaiduMap.setOnMarkerClickListener(overlay);
                overlay.setData(drivingRouteResult.getRouteLines().get(0));
                overlay.addToMap();
                overlay.zoomToSpan();
                //Toast.makeText(GuideActivity.this,"点击图标会有指示哦～",
                //        Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

        }

        @Override
        public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

        }

        @Override
        public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

        }
    };

    OnGetGeoCoderResultListener Listener = new OnGetGeoCoderResultListener() {
        @Override
        public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
        }

        @Override
        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
            if (reverseGeoCodeResult != null && reverseGeoCodeResult.error == SearchResult.ERRORNO.NO_ERROR) {
                ReverseGeoCodeResult.AddressComponent addressDetail = reverseGeoCodeResult.getAddressDetail();
                city = addressDetail.city;
            }
        }
    };

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0:
                mBaiduMap.clear();
                initIcon();
                mSearch.transitSearch(new TransitRoutePlanOption().from(
                        stNode).city(city).to(enNode));
                break;
            case 1:
                mBaiduMap.clear();
                initIcon();
                mSearch.drivingSearch(new DrivingRoutePlanOption().from(
                        stNode).to(enNode));
                break;
            case 2:
                mBaiduMap.clear();
                initIcon();
                mSearch.walkingSearch(new WalkingRoutePlanOption().from(
                        stNode).to(enNode));
                break;
        }

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onBackPressed() {
        if (tabLayout.getVisibility() == View.VISIBLE) {
            mBaiduMap.clear();
            initIcon();
            tabLayout.setVisibility(View.GONE);
        } else {
            super.onBackPressed();
        }
    }

    private void initIcon() {
        for (int i = 0; i < listBeans.size(); i++) {
            LatLng latLng = new LatLng(listBeans.get(i).getYAxis(), listBeans.get(i).getXAxis());
            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_openmap_mark);
            MarkerOptions option = new MarkerOptions().position(latLng)
                    .icon(bitmap).zIndex(9).draggable(true);
            Marker overlay = (Marker) mBaiduMap.addOverlay(option);
            Bundle bundle = new Bundle();
            bundle.putString("name", listBeans.get(i).getName());
            bundle.putString("phone", listBeans.get(i).getPhone());
            bundle.putString("responsibility", listBeans.get(i).getResponsibilityName());
            overlay.setExtraInfo(bundle);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locService.unregisterListener(listener);
        locService.stop();
        mSearch.destroy();
        geocode.destroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();

    }

    private void getPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<String>();
            /***
             * 定位权限为必须权限，用户如果禁止，则每次进入都会申请
             */
            // 定位精确位置
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
            /*
             * 读写权限和电话状态权限非必要权限(建议授予)只会申请一次，用户同意或者禁止，只会弹一次
			 */
            // 读写权限
            if (addPermission(permissions, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissionInfo += "Manifest.permission.WRITE_EXTERNAL_STORAGE Deny \n";
            }
            // 读取电话状态权限
            if (addPermission(permissions, Manifest.permission.READ_PHONE_STATE)) {
                permissionInfo += "Manifest.permission.READ_PHONE_STATE Deny \n";
            }

            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), SDK_PERMISSION_REQUEST);
            }
        }
    }

    @TargetApi(23)
    private boolean addPermission(ArrayList<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) { // 如果应用没有获得对应权限,则添加到列表中,准备批量申请
            if (shouldShowRequestPermissionRationale(permission)) {
                return true;
            } else {
                permissionsList.add(permission);
                return false;
            }

        } else {
            return true;
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // TODO Auto-generated method stub
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 授权成功，继续打电话
                    CallPhone();
                } else {
                    // 授权失败！
                    Toast.makeText(this, "授权失败！", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    static class organizeList {

        private String appKey;
        private String timeSpan;
        private String mobileType;
        private String pageNo;
        private String pageCount;

        public organizeList(String appKey, String timeSpan, String mobileType, String pageNo, String pageCount) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.mobileType = mobileType;
            this.pageNo = pageNo;
            this.pageCount = pageCount;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
