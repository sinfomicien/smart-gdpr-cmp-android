package com.smartadserver.android.smartcmp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Switch;
import android.widget.TextView;

import com.smartadserver.android.smartcmp.R;
import com.smartadserver.android.smartcmp.manager.ConsentManager;
import com.smartadserver.android.smartcmp.model.Purpose;

/**
 * Purpose activity.
 */

public class PurposeActivity extends AppCompatActivity {

    Purpose purpose;
    Switch purposeSwitch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purpose_activity_layout);

        // Setup the actionBar if any
        if (getSupportActionBar() != null) {
            getSupportActionBar().setSubtitle(ConsentManager.getSharedInstance().getConsentToolConfiguration().getConsentManagementPurposeDetailTitle());
        }

        purpose = getIntent().getParcelableExtra("purpose");

        bindViews();
    }

    private void bindViews() {
        purposeSwitch = findViewById(R.id.allowed_switch);
        purposeSwitch.setChecked(getIntent().getBooleanExtra("purpose_status", false));

        // Setup the allowed textview
        TextView allowedTextView = findViewById(R.id.allowed_textview);
        allowedTextView.setText(ConsentManager.getSharedInstance().getConsentToolConfiguration().getConsentManagerPurposeDetailAllowText());

        // Setup the purpose name textview
        TextView nameTextView = findViewById(R.id.purpose_name_textview);
        nameTextView.setText(purpose.getName());

        // Setup the purpose description textview
        TextView descriptionTextView = findViewById(R.id.purpose_description_textview);
        descriptionTextView.setText(purpose.getDescription());
    }

    @Override
    public void onBackPressed() {
        Intent result = new Intent();
        result.putExtra("purpose_status", purposeSwitch.isChecked());
        result.putExtra("purpose", purpose);
        setResult(RESULT_OK, result);
        finish();
    }
}