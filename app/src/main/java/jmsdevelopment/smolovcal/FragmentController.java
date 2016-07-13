package jmsdevelopment.smolovcal;

import android.app.Activity;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import jmsdevelopment.smolovcal.fragments.MainFragment;

/**
 * Created by Jesper on 30/06/2016.
 */

public class FragmentController {

    private static FragmentController controller;

    public static FragmentController get() {
        if (controller == null) {
            controller = new FragmentController();
        }
        return controller;
    }

    private FragmentController() {
    }

    public void setToolbarTitle(String title) {
        MainActivity.toolbar.setTitle(title);
    }

    public void returnToHome(Activity activity) {
        FragmentManager fragmentManager = ((MainActivity) activity).getSupportFragmentManager();

        // First, clear back stack
        if (fragmentManager.getBackStackEntryCount() != 0) {
            fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        transactFragments(activity, new MainFragment(), "start_fragment");
    }

    public void transactDialogFragment(FragmentActivity fragmentActivity, DialogFragment fragment, String backStackTag) {
        if (fragment != null) {
            FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
            if (backStackTag != null) {
                fragment.show(fragmentManager, backStackTag);
            } else {
                fragment.show(fragmentManager, "");
            }
        }
    }

    public void transactFragments(Activity activity, Fragment fragment, String backStackTag) {
        if (fragment != null) {
            Log.d(FragmentController.class.getSimpleName(), "Fragment " + fragment.toString());
            FragmentManager fragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (backStackTag != null) {
                fragmentTransaction.addToBackStack(backStackTag);
            }
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commitAllowingStateLoss();
            Log.d(FragmentController.class.getSimpleName(), "Fragment replace");
        }
    }

    public boolean transaftFragmentWithAnimations(Activity activity, Fragment fragment, String backStackTag) {
        if (fragment != null) {
            Log.d(FragmentController.class.getSimpleName(), "Fragment " + fragment.toString());
            FragmentManager fragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
            if (backStackTag != null) {
                fragmentTransaction.addToBackStack(backStackTag);
            }
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commitAllowingStateLoss();
            Log.d(FragmentController.class.getSimpleName(), "Fragment replace");
            return true;
        }
        return false;
    }
}
