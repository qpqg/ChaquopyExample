package python.qz;

import androidx.appcompat.app.AppCompatActivity;
import android.os.*;
import android.util.Log;
import android.content.Context;
import android.widget.Toast;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import python.qz.databinding.ActivityMainBinding;

import android.view.View;

public class MainActivity extends AppCompatActivity {

  private ActivityMainBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // Remove this line if you don't want AndroidIDE to show this app's logs

    super.onCreate(savedInstanceState);
    // Inflate and get instance of binding
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    // set content view to binding's root
    setContentView(binding.getRoot());

    if (!Python.isStarted()) {
      Python.start(new AndroidPlatform(this));
    }
    
    binding.run.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View arg0) {
             Python py = Python.getInstance();
                PyObject pyObject = py.getModule("script");
                
                PyObject obj = pyObject.callAttr("main",binding.codearea.getText().toString());
                binding.output.setText(obj.toString());
            // TODO: Implement this method
          }
        });
  }
}
