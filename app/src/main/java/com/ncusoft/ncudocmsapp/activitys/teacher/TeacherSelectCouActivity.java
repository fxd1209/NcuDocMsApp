package com.ncusoft.ncudocmsapp.activitys.teacher;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ncusoft.ncudocmsapp.ClientApplication;
import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.activitys.course.SelectCourseAdapter;
import com.ncusoft.ncudocmsapp.pojo.Course;
import com.ncusoft.ncudocmsapp.pojo.User;
import com.ncusoft.ncudocmsapp.service.course.CourseService;
import com.ncusoft.ncudocmsapp.service.course.CourseServiceInterface;
import com.ncusoft.ncudocmsapp.service.user.TeacherService;
import com.ncusoft.ncudocmsapp.service.user.TeacherServiceInterface;
import com.ncusoft.ncudocmsapp.utils.ToastUtil;

import java.util.List;
import java.util.Map;


public class TeacherSelectCouActivity extends Activity {
    private CourseServiceInterface courseService;
    private TeacherServiceInterface teacherService;
    private GridView gridView;
    private List<Course> courseList;
    private Map<String,Course> mapSelected; //记录被选中课程，添加进数据库
    private SelectCourseAdapter courseAdapter;


    private ImageButton btnSureSelCourse;
    private Spinner spinTerm;
    private Spinner spinClassCount;
    private String term;
    private String classCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_selectcourse);
        initData();
        initBindListener();

//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//                courseAdapter.setCurrentSel();
////                AlertDialog.Builder builder= new AlertDialog.Builder(TeacherSelectCouActivity.this);
////                builder.setTitle("提示").setMessage(dataList.get(arg2).get("text").toString()).create().show();
//            }
//        });
    }

    public void initData(){
        gridView = (GridView) findViewById(R.id.teacher_selectc_gridview);
        btnSureSelCourse=(ImageButton)findViewById(R.id.btn_teacher_sureSelCourse);
        spinTerm=(Spinner)findViewById(R.id.spinner_selectCou_term);
        spinClassCount=(Spinner)findViewById(R.id.spinner_selectCou_classCount);
        term="";
        classCount="";

        courseService=new CourseService();
        teacherService=new TeacherService();
        courseList=courseService.getAllCourseList();
        courseAdapter=new SelectCourseAdapter(TeacherSelectCouActivity.this,courseList);
        gridView.setAdapter(courseAdapter);

    }
    public void initBindListener(){
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                courseAdapter.setCurrentSel(position);
                courseAdapter.refreshDataSet(); //不通知courseAdapter不会调用getView()
            }
        });
        spinTerm.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                term = spinTerm.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinClassCount.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                classCount = spinClassCount.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnSureSelCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (term.equals("") || term==null){
                    ToastUtil.initToast(TeacherSelectCouActivity.this,
                            ToastUtil.ToastType.FAIL,
                            "请选择学期", Toast.LENGTH_LONG,new Point(0,0)).show();
                    return;
                }
                if (classCount.equals("") || classCount==null){
                    ToastUtil.initToast(TeacherSelectCouActivity.this,
                            ToastUtil.ToastType.FAIL,
                            "请选择班级", Toast.LENGTH_LONG,new Point(0,0)).show();
                    return;
                }
                mapSelected=courseAdapter.getSelectedCourse();
                if (mapSelected.size()<=0){
                    ToastUtil.initToast(TeacherSelectCouActivity.this,
                            ToastUtil.ToastType.FAIL,
                            "请选择课程", Toast.LENGTH_LONG,new Point(0,0)).show();
                }else {
                    if(teacherService.selectedCourse(term,classCount,mapSelected)){
                        ToastUtil.initToast(TeacherSelectCouActivity.this,
                                ToastUtil.ToastType.SUCCESS,
                                "选择成功", Toast.LENGTH_LONG,new Point(0,0)).show();
                    }else{
                        ToastUtil.initToast(TeacherSelectCouActivity.this,
                                ToastUtil.ToastType.SUCCESS,
                                "全部或部分选择失败", Toast.LENGTH_LONG,new Point(0,0)).show();
                    }
                }
            }
        });
    }

}


//    private GridView gridView;
//        private List<Map<String, Object>> dataList;
//        private SimpleAdapter adapter;
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_teacher_selectcourse);
//            gridView = (GridView) findViewById(R.id.gridview);
//            //初始化数据
//            initData();
//
//            String[] from={"img","text"};
//
//            int[] to={R.id.img,R.id.text};
//
//            adapter=new SimpleAdapter(this, dataList, R.layout.course_select_grid, from, to);
//
//            gridView.setAdapter(adapter);
//
//            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//                                        long arg3) {
//                    AlertDialog.Builder builder= new AlertDialog.Builder(TeacherSelectCourse.this);
//                    builder.setTitle("提示").setMessage(dataList.get(arg2).get("text").toString()).create().show();
//                }
//            });
//        }
//
//        void initData() {
//            //图标
//            int icno[] = { R.drawable.dianzan, R.drawable.dianzan, R.drawable.dianzan,
//                    R.drawable.dianzan, R.drawable.dianzan, R.drawable.dianzan, R.drawable.dianzan,
//                    R.drawable.dianzan, R.drawable.dianzan, R.drawable.dianzan, R.drawable.dianzan, R.drawable.dianzan };
//            //图标下的文字
//            String name[]={"时钟","信号","宝箱","秒钟","大象","FF","记事本","书签","印象","商店","主题","迅雷"};
//            dataList = new ArrayList<Map<String, Object>>();
//            for (int i = 0; i <icno.length; i++) {
//                Map<String, Object> map=new HashMap<String, Object>();
//                map.put("img", icno[i]);
//                map.put("text",name[i]);
//                dataList.add(map);
//            }
//        }

