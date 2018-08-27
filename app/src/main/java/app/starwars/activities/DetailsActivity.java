package app.starwars.activities;

import android.os.Bundle;
import android.widget.TextView;

import app.starwars.R;
import app.starwars.pojo.CharacterListModel;
import app.starwars.utilities.Utilities;

/**
 * Created by apple on 26/08/18.
 */

public class DetailsActivity extends BaseActivity {

    TextView createdDetails,massDetails,heightDetails,nameDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

            initViews();
    }

    private void initViews()
    {
        createdDetails=findViewById(R.id.createdDetails);
        massDetails=findViewById(R.id.massDetails);
        heightDetails=findViewById(R.id.heightDetails);
        nameDetail=findViewById(R.id.nameDetail);
        getData();
    }

    private void getData()
    {
        CharacterListModel characterListModel=(CharacterListModel)getIntent().getSerializableExtra("data");
        createdDetails.setText(Utilities.convertDateFormats(characterListModel.getCreated(),"yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ","yyyy-MM-dd HH:mm"));
        massDetails.setText(characterListModel.getMass()+" Kg");
        heightDetails.setText(Utilities.cmToMeters(Integer.parseInt(characterListModel.getHeight()))+" mtr");
        nameDetail.setText(characterListModel.getName());
    }

}
