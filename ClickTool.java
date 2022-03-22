package com.gordonlu.clicktool;

import android.app.Activity;
import android.content.Context;
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;

import com.google.appinventor.components.runtime.AndroidViewComponent;
import android.view.View;
import android.view.DragEvent;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnDragListener;

@DesignerComponent(
        version = 1,
        description = "A non-visible extension that is used to react to clicks and long clicks for visible components.",
        category = ComponentCategory.EXTENSION,
        nonVisible = true,
        iconName = "https://docs.google.com/drawings/d/e/2PACX-1vQCI87PHLBF0jb8QWyYmIRQSjjNW3EFXf-qpsWCvBYkUQ9vEgPAB8SpxcMpblxNpbIYrjCjLrRLIU2c/pub?w=16&h=16")

@SimpleObject(external = true)
//Libraries
@UsesLibraries(libraries = "")
//Permissions
@UsesPermissions(permissionNames = "")

public class ClickTool extends AndroidNonvisibleComponent {

    //Activity and Context
    private Context context;
    private Activity activity;

    public ClickTool(ComponentContainer container){
        super(container.$form());
        this.activity = container.$context();
        this.context = container.$context();
    }

    @SimpleFunction(description = "Registers the component so that when the user clicks the component, it will fire the OnClick event.")
    public void RegisterClick(final AndroidViewComponent component) {
        View view = component.getView();
            view.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        OnClick(component);	
        }
    });
    }

    @SimpleFunction(description = "Registers the component so that when the user long clicks the component, it will fire the OnLongClick event.")
    public void RegisterLongClick(final AndroidViewComponent component) {
        View view = component.getView();
        view.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
          OnLongClick(component);
          return false;
        }
      });
    }

    @SimpleEvent(description = "This event is fired when a registered component is clicked.")
    public void OnClick(AndroidViewComponent component){
        EventDispatcher.dispatchEvent(this, "OnClick", component);
    }

    @SimpleEvent(description = "This event is fired when a registered component is long clicked.")
    public void OnLongClick(AndroidViewComponent component){
        EventDispatcher.dispatchEvent(this, "OnLongClick", component);
    }
}
