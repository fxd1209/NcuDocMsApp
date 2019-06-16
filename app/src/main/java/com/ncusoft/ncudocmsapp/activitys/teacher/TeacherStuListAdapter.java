package com.ncusoft.ncudocmsapp.activitys.teacher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.pojo.StudentCourse;

import java.util.List;

public class TeacherStuListAdapter extends BaseAdapter {

    private Context context;//声明适配器中引用的上下文
    private LayoutInflater layoutInflater; //布局加载器
    private List<StudentCourse> scList;
    private ViewHolder viewHolder=null;

    public TeacherStuListAdapter(Context context, List<StudentCourse> scList){
        this.context=context;
        this.scList=scList;
        layoutInflater=LayoutInflater.from(context);
    }
    public List<StudentCourse> getScList() {
        return scList;
    }
    public void setScList(List<StudentCourse> scList) {
        this.scList = scList;
    }

    /**
     * 数据量
     * @return
     */
    @Override
    public int getCount() {
        return scList.size();
    }

    @Override
    public Object getItem(int position) {
        return scList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.teacher_item_stulist,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.stuNameTxtV.setText(scList.get(position).getStudent().getName());
        viewHolder.stuIdTxtV.setText(scList.get(position).getStudent().getId());
        viewHolder.stuSexTxtV.setText(scList.get(position).getStudent().getSex());
        return convertView;
    }
    public void remove(int index){
        if (index>=0 && index <scList.size())
            scList.remove(index);
    }
    public void refreshDataSet(){
        notifyDataSetChanged();
    }

    class ViewHolder{
        private TextView stuNameTxtV;
        private TextView stuIdTxtV;
        private TextView stuSexTxtV;
        private View itemView;

        public ViewHolder(View itemView){
            if (itemView==null)
                throw new IllegalArgumentException("itemView can not be null!");
            this.itemView=itemView;
            stuNameTxtV=(TextView)itemView.findViewById(R.id.teacher_stuList_name);
            stuIdTxtV=(TextView)itemView.findViewById(R.id.teacher_stuList_stuId);
            stuSexTxtV=(TextView)itemView.findViewById(R.id.teacher_stuList_sex);
        }
    }
}
