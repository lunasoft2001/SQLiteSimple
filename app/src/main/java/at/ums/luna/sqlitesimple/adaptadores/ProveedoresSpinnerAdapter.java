package at.ums.luna.sqlitesimple.adaptadores;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import at.ums.luna.sqlitesimple.modelos.Proveedores;

/**
 * Created by luna-aleixos on 24.05.2016.
 */
public class ProveedoresSpinnerAdapter extends ArrayAdapter<Proveedores> {

    private Context mContext;
    private List<Proveedores> mValues;

    public ProveedoresSpinnerAdapter(Context context, int textViewResourceId, List <Proveedores> objects){
        super(context,textViewResourceId,objects);
        this.mContext = context;
        this.mValues = objects;
    }

    @Override
    public int getCount() {
        return mValues.size();
    }

    @Override
    public Proveedores getItem(int position) {
        return mValues.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //ESTO ES PARA EL PRIMER ITEM ANTES DE SELECCIONAR O VALOR POR DEFECTO
        TextView label = new TextView(mContext);
        label.setTextColor(Color.BLACK);
        label.setTextSize(18);
        label.setText(" " + mValues.get(position).getNombre());
        label.setHeight(50);
        label.setGravity(Gravity.LEFT | Gravity.CENTER);

        return label;

    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        //This is when user click the spinner and list of item display
        // beneath it
        TextView label = new TextView(mContext);
        label.setTextColor(Color.BLACK);
        label.setTextSize(18);
        label.setText(" " + mValues.get(position).getNombre());
        label.setHeight(70);
        label.setGravity(Gravity.LEFT | Gravity.CENTER );

        return label;
    }
}

