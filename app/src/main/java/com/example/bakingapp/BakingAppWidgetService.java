package com.example.bakingapp;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.bakingapp.model.Ingredient;

import java.util.ArrayList;

public class BakingAppWidgetService extends RemoteViewsService {
    private ArrayList<Ingredient> ingredients;
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteTextFactoryService(getApplicationContext());
    }

    class RemoteTextFactoryService implements BakingAppWidgetService.RemoteViewsFactory{

        final Context context;

        public RemoteTextFactoryService(Context context) {
            this.context = context;
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            ingredients = BakingAppProvider.ingredients;
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return ingredients == null ? 0 : ingredients.size();
        }

        @Override
        public RemoteViews getViewAt(int index) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_item);
            Ingredient ingredient = ingredients.get(index);
            String text = ingredient.getQuantity() + " " + String.valueOf(ingredient.getQuantity()) + " " + ingredient.getMeasure();
            views.setTextViewText(R.id.tv_ingredient_widget_item, text);
            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 0;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }
}
