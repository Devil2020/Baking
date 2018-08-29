package comc.example.mohammedmorse.baking.Widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;

import comc.example.mohammedmorse.baking.Retrofit.IngrediantsModel;

/**
 * Created by Mohammed Morse on 28/08/2018.
 */

public class RecipeWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RecipeWidgetFactory(this.getApplicationContext());
    }
    public class RecipeWidgetFactory implements RemoteViewsFactory{
        Context context;
        ArrayList<IngrediantsModel> list;
        public RecipeWidgetFactory(Context context){
            this.context=context;
        }
        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {

        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public RemoteViews getViewAt(int i) {
            return null;
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
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }
}
