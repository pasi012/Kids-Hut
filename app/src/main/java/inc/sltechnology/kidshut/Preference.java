package inc.sltechnology.kidshut;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;

import androidx.annotation.Nullable;

public class Preference extends PreferenceActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.root_preferences);

        Load_Settings();

    }

    private void Load_Settings(){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        boolean Check_night = sharedPreferences.getBoolean("NIGHT", false);
//
//        if (Check_night){
//
//            getListView().setBackgroundColor(Color.parseColor("#2222"));
//
//        }else {
//
//            getListView().setBackgroundColor(Color.parseColor("#ffff"));
//
//        }
//
//        CheckBoxPreference checkNight = (CheckBoxPreference) findPreference("NIGHT");
//        checkNight.setOnPreferenceChangeListener(new android.preference.Preference.OnPreferenceChangeListener() {
//            @Override
//            public boolean onPreferenceChange(android.preference.Preference root_references, Object object) {
//
//                boolean yes = (boolean) object;
//
//                if (yes){
//
//                    getListView().setBackgroundColor(Color.parseColor("#2222"));
//
//                }else {
//
//                    getListView().setBackgroundColor(Color.parseColor("#ffff"));
//
//                }
//
//                return true;
//            }
//        });

        ListPreference LP = (ListPreference) findPreference("ORIENTATION");
        String orientation = sharedPreferences.getString("ORIENTATION", "false");

        if ("1".equals(orientation)){

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);
            LP.setSummary(LP.getEntry());

        }else if ("2".equals(orientation)){

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            LP.setSummary(LP.getEntry());

        }else if ("3".equals(orientation)){

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            LP.setSummary(LP.getEntry());

        }

        LP.setOnPreferenceChangeListener(new android.preference.Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(android.preference.Preference preference, Object object) {

                String items = (String) object;

                if (preference.getKey().equals("ORIENTATION")){

                    switch (items){

                        case "1":
                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);
                            break;

                        case "2":
                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                            break;

                        case "3":
                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                            break;

                    }

                    ListPreference listPreference = (ListPreference) preference;
                    listPreference.setSummary(listPreference.getEntries()[listPreference.findIndexOfValue(items)]);

                }

                return true;
            }
        });

    }

    @Override
    protected void onResume() {

        Load_Settings();

        super.onResume();
    }
}
