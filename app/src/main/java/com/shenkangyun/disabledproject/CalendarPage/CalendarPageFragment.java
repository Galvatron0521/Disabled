package com.shenkangyun.disabledproject.CalendarPage;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.ldf.calendar.Utils;
import com.ldf.calendar.component.CalendarAttr;
import com.ldf.calendar.component.CalendarViewAdapter;
import com.ldf.calendar.interf.OnSelectDateListener;
import com.ldf.calendar.model.CalendarDate;
import com.ldf.calendar.view.Calendar;
import com.ldf.calendar.view.MonthPager;
import com.shenkangyun.disabledproject.BaseFolder.Base;
import com.shenkangyun.disabledproject.BeanFolder.BwlBean;
import com.shenkangyun.disabledproject.BeanFolder.CalendarBean;
import com.shenkangyun.disabledproject.BeanFolder.ResultBean;
import com.shenkangyun.disabledproject.R;
import com.shenkangyun.disabledproject.UtilsFolder.CustomDayView;
import com.shenkangyun.disabledproject.UtilsFolder.EditTextDialog;
import com.shenkangyun.disabledproject.UtilsFolder.GsonCallBack;
import com.shenkangyun.disabledproject.UtilsFolder.MemorandumDaoUtils;
import com.shenkangyun.disabledproject.UtilsFolder.RecyclerViewDivider;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/2/23.
 */

public class CalendarPageFragment extends Fragment {

    @BindView(R.id.top_year)
    TextView textViewYearDisplay;
    @BindView(R.id.top_mounth)
    TextView textViewMonthDisplay;
    @BindView(R.id.top_jt)
    TextView topJt;
    @BindView(R.id.top_sy)
    TextView lastMonthBtn;
    @BindView(R.id.top_xx)
    TextView nextMonthBtn;
    @BindView(R.id.top_ss)
    ImageView scrollSwitch;
    @BindView(R.id.calendar_view)
    MonthPager monthPager;
    @BindView(R.id.list)
    RecyclerView rvToDoList;
    @BindView(R.id.content)
    CoordinatorLayout content;
    @BindView(R.id.ActionButton)
    FloatingActionButton ActionButton;

    private ArrayList<Calendar> currentCalendars = new ArrayList<>();
    private List<CalendarBean.DataBean.ListBean> listBeans = new ArrayList<>();
    private List<BwlBean> bwlBeans = new ArrayList<>();
    private CalendarViewAdapter calendarAdapter;
    private OnSelectDateListener onSelectDateListener;
    private int mCurrentPage = MonthPager.CURRENT_DAY_INDEX;
    private Context context;
    private CalendarDate currentDate;
    private boolean initiated = false;
    private AlertDialog alertDialog;
    private MemorandumDaoUtils dataBase;
    private SimpleDateFormat sdf;
    private View.OnTouchListener otl;
    private EventAdapter eventAdapter;
    private LinearLayoutManager layoutManager;

    private long end;
    private long start;
    private String title;
    private int calendarid;
    private String calendardescribe;
    private List<BwlBean> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, null);
        ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    public void refreshList() {
        list = dataBase.queryMemorandumByDate(currentDate);
        eventAdapter = new EventAdapter(R.layout.item_events, list);
        layoutManager = new LinearLayoutManager(getContext());
        rvToDoList.setLayoutManager(layoutManager);
        rvToDoList.setAdapter(eventAdapter);
        initClick();
    }

    private void initView() {
        context = getContext();
        dataBase = new MemorandumDaoUtils(getContext());
        sdf = new SimpleDateFormat("hh:mm:ss");
        rvToDoList.setHasFixedSize(true);
        //此处强行setViewHeight，毕竟你知道你的日历牌的高度
        monthPager.setViewheight(Utils.dpi2px(context, 270));
        //这里用线性显示 类似于ListView
        rvToDoList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvToDoList.addItemDecoration(new RecyclerViewDivider(getContext(), LinearLayoutManager.VERTICAL, 10, ContextCompat.getColor(getContext(), R.color.split_color)));
        initCurrentDate();
        initCalendarView();
        initToolbarClickListener();
        alertDialog = EditTextDialog.init(R.layout.layout_dialog, getContext(), new EditTextDialog.CallBack() {
            @Override
            public void Confirm(BwlBean bean) {
                bean.setDay(currentDate.getDay());
                bean.setMouth(currentDate.getMonth());
                bean.setYear(currentDate.getYear());
                bean.setTime(sdf.format(new Date()));
                if (bean.getTitle().isEmpty() && bean.getContent().isEmpty()) {
                    Toast.makeText(context, "请将资料填写完整", Toast.LENGTH_SHORT).show();
                } else {
                    dataBase.insertMemorandum(bean);
                    initInsert();
                }
                refreshList();
                initMarkData();
            }
        });
        rvToDoList.setOnTouchListener(otl);
    }

    private void initData() {
        refreshList();
        initMarkData();
        OkHttpUtils.post().url(Base.URL)
                .addParams("act", "selectCalendarList")
                .addParams("data", new selectCalendarList(Base.getMD5Str(), Base.getTimeSpan(), Base.getUserID()).toJson())
                .build().execute(new GsonCallBack<CalendarBean>() {
            @Override
            public void onSuccess(CalendarBean response) throws JSONException {
                for (int i = 0; i < response.getData().getList().size(); i++) {
                    CalendarBean.DataBean.ListBean listBean = new CalendarBean.DataBean.ListBean();
                    end = response.getData().getList().get(i).getEnd();
                    start = response.getData().getList().get(i).getStart();
                    title = response.getData().getList().get(i).getTitle();
                    calendarid = response.getData().getList().get(i).getCalendarid();
                    calendardescribe = response.getData().getList().get(i).getCalendardescribe();
                    listBean.setEnd(end);
                    listBean.setStart(start);
                    listBean.setTitle(title);
                    listBean.setCalendarid(calendarid);
                    listBean.setCalendardescribe(calendardescribe);
                    listBeans.add(listBean);
                }
                initMemorandum();
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }


    private void initMemorandum() {
        dataBase.deleteAll();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        for (int i = 0; i < listBeans.size(); i++) {
            long start = listBeans.get(i).getStart();
            String startFormat = dateFormat.format(start);
            int daoYear = Integer.parseInt(startFormat.substring(0, startFormat.indexOf("年")));
            int daoMonth = Integer.parseInt(startFormat.substring(startFormat.indexOf("年") + 1, startFormat.indexOf("月")));
            int daoDay = Integer.parseInt(startFormat.substring(startFormat.indexOf("月") + 1, startFormat.indexOf("日")));
            BwlBean bwlBean = new BwlBean();
            bwlBean.setYear(daoYear);
            bwlBean.setMouth(daoMonth);
            bwlBean.setDay(daoDay);
            bwlBean.setStart(String.valueOf(listBeans.get(i).getStart()));
            bwlBean.setEnd(String.valueOf(listBeans.get(i).getEnd()));
            bwlBean.setTitle(listBeans.get(i).getTitle());
            bwlBean.setContent(listBeans.get(i).getCalendardescribe());
            bwlBean.set_id(Long.valueOf(listBeans.get(i).getCalendarid()));
            bwlBeans.add(bwlBean);
            dataBase.insertMultMemorandum(bwlBeans);
        }
    }


    private void initInsert() {
        List<BwlBean> list = dataBase.queryMemorandumByDate(currentDate);
        int index = list.size() - 1;
        OkHttpUtils.post().url(Base.URL)
                .addParams("act", "insertOrUpdateCalendar")
                .addParams("data", new insertOrUpdateCalendar("insert", Base.getMD5Str(), Base.getTimeSpan(), "1", Base.getUserID(),
                        list.get(index).getTitle(), list.get(index).getContent(), list.get(index).getStart(), list.get(index).getEnd()).toJson())
                .build().execute(new GsonCallBack<ResultBean>() {
            @Override
            public void onSuccess(ResultBean response) throws JSONException {
                String status = response.getStatus();
                if ("0".equals(status)) {
                    initData();
                    Toast.makeText(context, response.getData().getData(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, response.getData().getData(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void initClick() {
        eventAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.content:
                        List<BwlBean> list = dataBase.queryMemorandumByDate(currentDate);
                        Intent intent = new Intent(getActivity(), CalenderInfoActivity.class);
                        intent.putExtra("title", list.get(position).getTitle());
                        intent.putExtra("Content", list.get(position).getContent());
                        intent.putExtra("Start", list.get(position).getStart());
                        intent.putExtra("End", list.get(position).getEnd());
                        startActivity(intent);
                        break;
                    case R.id.right:
                        List<BwlBean> listDelete = dataBase.queryMemorandumByDate(currentDate);
                        initDelete(position);
                        dataBase.deleteMemorandum(listDelete.get(position));
                        refreshList();
                        break;
                    case R.id.left:
                        List<BwlBean> list1 = dataBase.queryMemorandumByDate(currentDate);
                        Intent intent1 = new Intent(getActivity(), CalenderEditingActivity.class);
                        intent1.putExtra("Calendarid", list1.get(position).get_id());
                        intent1.putExtra("title", list1.get(position).getTitle());
                        intent1.putExtra("Content", list1.get(position).getContent());
                        intent1.putExtra("Start", list1.get(position).getStart());
                        intent1.putExtra("End", list1.get(position).getEnd());
                        startActivityForResult(intent1, 0);
                        break;
                }
            }
        });
    }

    private void initDelete(int position) {
        List<BwlBean> list = dataBase.queryMemorandumByDate(currentDate);
        Long id = list.get(position).get_id();
        String calendarId = String.valueOf(id);
        String userID = Base.getUserID();
        Integer userId = Integer.valueOf(userID);
        OkHttpUtils.post().url(Base.URL)
                .addParams("act", "deleteCalendar")
                .addParams("data", new deleteCalendar(Base.getMD5Str(), Base.getTimeSpan(), "1", userId, Integer.valueOf(calendarId)).toJson())
                .build()
                .execute(new GsonCallBack<ResultBean>() {
                    @Override
                    public void onSuccess(ResultBean response) throws JSONException {
                        String status = response.getStatus();
                        if ("0".equals(status)) {
                            initData();
                        } else {
                            Toast.makeText(context, response.getData().getData(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initMarkData() {
        HashMap<String, String> markData = new HashMap<>();
        List<BwlBean> lists = dataBase.queryAllRecord();
        for (BwlBean bean : lists) {
            markData.put(bean.getYear() + "-" + bean.getMouth() + "-" + bean.getDay(), "0");
        }
        calendarAdapter.setMarkData(markData);
    }

    /**
     * 初始化对应功能的listener
     *
     * @return void
     */
    private void initToolbarClickListener() {
        topJt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickBackToDayBtn();
            }
        });
        scrollSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (calendarAdapter.getCalendarType() == CalendarAttr.CalendayType.WEEK) {
                    nextMonthBtn.setText("下月");
                    lastMonthBtn.setText("上月");
                    Utils.scrollTo(content, rvToDoList, monthPager.getViewHeight(), 500);
                    ObjectAnimator.ofFloat(scrollSwitch, "rotation", 180f, 0f).setDuration(500).start();
                    calendarAdapter.switchToMonth();
                } else {
                    nextMonthBtn.setText("下周");
                    lastMonthBtn.setText("上周");
                    Utils.scrollTo(content, rvToDoList, monthPager.getCellHeight(), 500);
                    calendarAdapter.switchToWeek(monthPager.getRowIndex());
                    ObjectAnimator.ofFloat(scrollSwitch, "rotation", 0f, 180f).setDuration(500).start();
                }
            }
        });

        nextMonthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monthPager.setCurrentItem(monthPager.getCurrentPosition() + 1);
            }
        });
        lastMonthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monthPager.setCurrentItem(monthPager.getCurrentPosition() - 1);
            }
        });
    }

    /**
     * 初始化currentDate
     *
     * @return void
     */
    private void initCurrentDate() {
        currentDate = new CalendarDate();
        textViewYearDisplay.setText(currentDate.getYear() + "年");
        textViewMonthDisplay.setText(currentDate.getMonth() + "月");
    }

    /**
     * 初始化CustomDayView，并作为CalendarViewAdapter的参数传入
     *
     * @return void
     */
    private void initCalendarView() {
        initListener();
        CustomDayView customDayView = new CustomDayView(context, R.layout.custom_day);
        calendarAdapter = new CalendarViewAdapter(
                context,
                onSelectDateListener,
                CalendarAttr.CalendayType.MONTH,
                customDayView);
        initMarkData();
        initMonthPager();
    }

    private void initListener() {
        onSelectDateListener = new OnSelectDateListener() {
            @Override
            public void onSelectDate(CalendarDate date) {
                refreshClickDate(date);
            }

            @Override
            public void onSelectOtherMonth(int offset) {
                //偏移量 -1表示刷新成上一个月数据 ， 1表示刷新成下一个月数据
                monthPager.selectOtherMonth(offset);
            }
        };
    }

    private void refreshClickDate(CalendarDate date) {
        currentDate = date;
        textViewYearDisplay.setText(date.getYear() + "年");
        textViewMonthDisplay.setText(date.getMonth() + "月");
        refreshList();
    }

    /**
     * 初始化monthPager，MonthPager继承自ViewPager
     *
     * @return void
     */
    private void initMonthPager() {
        monthPager.setAdapter(calendarAdapter);
        monthPager.setCurrentItem(MonthPager.CURRENT_DAY_INDEX);
        monthPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                position = (float) Math.sqrt(1 - Math.abs(position));
                page.setAlpha(position);
            }
        });
        monthPager.addOnPageChangeListener(new MonthPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPage = position;
                currentCalendars = calendarAdapter.getPagers();
                if (currentCalendars.get(position % currentCalendars.size()) instanceof Calendar) {
                    CalendarDate date = currentCalendars.get(position % currentCalendars.size()).getSeedDate();
                    currentDate = date;
                    textViewYearDisplay.setText(date.getYear() + "年");
                    textViewMonthDisplay.setText(date.getMonth() + "月");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public void onClickBackToDayBtn() {
        refreshMonthPager();
        refreshList();
    }

    private void refreshMonthPager() {
        CalendarDate today = new CalendarDate();
        calendarAdapter.notifyDataChanged(today);
        textViewYearDisplay.setText(today.getYear() + "年");
        textViewMonthDisplay.setText(today.getMonth() + "月");

    }

    @OnClick(R.id.ActionButton)
    public void onViewClicked() {
        alertDialog.show();
    }

    static class selectCalendarList {

        private String appKey;
        private String timeSpan;
        private String id;

        public selectCalendarList(String appKey, String timeSpan, String id) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.id = id;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }

    static class deleteCalendar {

        private String appKey;
        private String timeSpan;
        private String mobileType;
        private int id;
        private int calendarid;

        public deleteCalendar(String appKey, String timeSpan, String mobileType, int id, int calendarid) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.mobileType = mobileType;
            this.id = id;
            this.calendarid = calendarid;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }

    static class insertOrUpdateCalendar {
        private String optionTag;
        private String appKey;
        private String timeSpan;
        private String mobileType;
        private String id;
        private String title;
        private String calendardescribe;
        private String start;
        private String end;

        public insertOrUpdateCalendar(String optionTag, String appKey, String timeSpan, String mobileType, String id,
                                      String title, String calendardescribe, String start, String end) {
            this.optionTag = optionTag;
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.mobileType = mobileType;
            this.id = id;
            this.title = title;
            this.calendardescribe = calendardescribe;
            this.start = start;
            this.end = end;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
