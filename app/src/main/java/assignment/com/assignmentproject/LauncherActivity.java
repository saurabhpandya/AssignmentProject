package assignment.com.assignmentproject;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import assignment.com.assignmentproject.databinding.ActivityLauncherBinding;
import assignment.com.assignmentproject.viewmodel.MobileDataUsageViewModel;

public class LauncherActivity extends AppCompatActivity {

    private ActivityLauncherBinding binding;
    private MobileDataUsageViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(LauncherActivity.this, R.layout.activity_launcher);
        viewModel = new MobileDataUsageViewModel();
        binding.setViewModel(viewModel);
        
    }

    private void getMobileDataUsage() {

    }
}
