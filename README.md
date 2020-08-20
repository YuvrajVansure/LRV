
# LRV 
**Learning RecyclerView by creating one**
<!--> This app will show list of Scout Regiment character from anime Attack on Titan-->

<!--I am using official documentation provided by ***[developer.android.com](https://developer.android.com/guide/topics/ui/layout/recyclerview)***-->
I am using LinearLayoutManager as LayoutManager and CardView for list item

### Adding dependencies
List itemAdd the dependencies for the artifacts you need in the build.gradle file for your app or module:
```gradle
dependencies {
	// Required Dependencies
    implementation 'androidx.recyclerview:recyclerview:1.2.0-alpha05'
    // For control over item selection of both touch and mouse driven selection
    implementation 'androidx.recyclerview:recyclerview-selection:1.1.0-rc01'
   
    // Optional but Recommended
    implementation 'androidx.cardview:cardview:1.0.0'
    implementation 'com.google.android.material:material:1.2.0'
}
```
 
### Custom Card for List
create custom layout for list item
> item_row.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.cardview.widget.CardView
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="8dp"
    app:cardCornerRadius="6dp"
    app:cardElevation="6dp">
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:padding="8dp"
        android:orientation="vertical">
        <TextView
            android:id="@+id/txtName"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:padding="1dp"
            android:textColor="@color/black"
            android:textSize="18sp" />
        <TextView
            android:id="@+id/txtRank"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:padding="1dp"
            android:textColor="@color/black" />
        <TextView
            android:id="@+id/txtKill"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:padding="1dp"
            android:textColor="@color/black" />
    </LinearLayout>

</androidx.cardview.widget.CardView>
```
### Create Data class for list items
Data/Modal class contains data structure for item in list 
> Scout.java 
```java
package com.senpai.lrv;

public class Scout {
    private String Name, Rank, KillCount;
    public Scout(String name, String rank, String killCount) {
        Name = name;
        Rank = rank;
        KillCount = killCount;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getRank() {
        return Rank;
    }
    public void setRank(String rank) {
        Rank = rank;
    }
    public String getKillCount() {
        return KillCount;
    }
    public void setKillCount(String killCount) {
        KillCount = killCount;
    }
}

```
### Create Custom Adapter
following is simple structure for Custom Adapter
> ScoutAdapter.java
```java
package com.senpai.lrv;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ScoutAdapter extends RecyclerView.Adapter<ScoutAdapter.ScoutHolder> {
    @NonNull
    @Override
    public ScoutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ScoutHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ScoutHolder extends RecyclerView.ViewHolder {
        public ScoutHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
```
>> above code block is empty structure and can be reused for other adapter by renaming classes
>>> Completed ScoutAdapter is [here](https://github.com/YuvrajVansure/LRV/wiki/_new#completed-customadapter)

* * *
>ScoutAdapter.java
```java
package com.senpai.lrv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ScoutAdapter extends RecyclerView.Adapter<ScoutAdapter.ScoutHolder> {
    private Context context;
    private ArrayList<Scout> scouts;

    public ScoutAdapter(Context context, ArrayList<Scout> scouts) {
        this.context = context;
        this.scouts = scouts;
    }

    @NonNull
    @Override
    public ScoutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row,parent,false);
        return new ScoutHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoutHolder holder, int position) {
        Scout scout = scouts.get(position);
        holder.setDetails(scout);
    }

    @Override
    public int getItemCount() {
        return scouts.size();
    }

    public class ScoutHolder extends RecyclerView.ViewHolder {
        private TextView txtName, txtRank, txtKill;
        public ScoutHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtRank = itemView.findViewById(R.id.txtRank);
            txtKill = itemView.findViewById(R.id.txtKill);
        }

        public void setDetails(Scout scout) {
            txtName.setText(scout.getName());
            txtRank.setText("Rank: "+scout.getRank());
            txtKill.setText("Rank: "+scout.getKillCount());
        }
    }
}
```

### Adding RecyclerView to Layout
Add recyclerview to layout file
> activity_main.xml
```xml	
	...
    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/scout_recycler_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:scrollbars="vertical"
        android:scrollbarStyle="outsideOverlay"/>
    ...
```

next step is to obtain a handle to the object, connect it to a layout manager, and attach an adapter for the data to be displayed:
>MainActivity.java
>> Declare Recyclerview , Adapter and ArrayList
```java
...
public class MainActivity extends AppCompatActivity {

    //Declare Recyclerview , Adapter and ArrayList 
    private RecyclerView recyclerView;
    private ScoutAdapter adapter;
    private ArrayList<Scout> scoutArrayList;
...
```
>> Initialize RecyclerView and set Adapter
```java
private void initView() {
        // Initialize RecyclerView and set Adapter
        recyclerView = findViewById(R.id.scout_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        scoutArrayList = new ArrayList<>();
        adapter = new ScoutAdapter(this,scoutArrayList);
        recyclerView.setAdapter(adapter);
        createList(); 
    }
```
>> create list
```java
private void createList() {
        //data to be shown in list
        scoutArrayList.add(new Scout( "Eren Jaeger" ,"Unranked" , "22" ));
        scoutArrayList.add(new Scout ( "Mikasa Ackermann" ,"Unranked" , "12" ) );
        scoutArrayList.add(new Scout ( "Armin Arlelt" ,"Unranked" , "0" ) );
        scoutArrayList.add(new Scout ( "Erwin Smith " , "Commander" , "Unknown" ) );
        scoutArrayList.add(new Scout ( "Levi Ackermann" , "Squad Captain" ,"~58" ) );
        scoutArrayList.add(new Scout ( "Hange Zoe" , "Commander" , "Unknown") );
        scoutArrayList.add(new Scout ( "Jean Kirschtein" , "Unranked" , "1" )	);
        scoutArrayList.add(new Scout ("Conny Springer", "Unranked" , "Unknown")	);
        scoutArrayList.add(new Scout ("Sasha Braus","Unranked","1")	);

    }
   ```

> Complete MainActivity.java
```java
package com.senpai.lrv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Declare Recyclerview , Adapter and ArrayList
    private RecyclerView recyclerView;
    private ScoutAdapter adapter;
    private ArrayList<Scout> scoutArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        // Initialize RecyclerView and set Adapter
        recyclerView = findViewById(R.id.scout_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        scoutArrayList = new ArrayList<>();
        adapter = new ScoutAdapter(this,scoutArrayList);
        recyclerView.setAdapter(adapter);
        createList();
    }

    private void createList() {
        //data to be shown in list
        scoutArrayList.add(new Scout( "Eren Jaeger" ,"Unranked" , "22" ));
        scoutArrayList.add(new Scout ( "Mikasa Ackermann" ,"Unranked" , "12" ) );
        scoutArrayList.add(new Scout ( "Armin Arlelt" ,"Unranked" , "0" ) );
        scoutArrayList.add(new Scout ( "Erwin Smith " , "Commander" , "Unknown" ) );
        scoutArrayList.add(new Scout ( "Levi Ackermann" , "Squad Captain" ,"~58" ) );
        scoutArrayList.add(new Scout ( "Hange Zoe" , "Commander" , "Unknown") );
        scoutArrayList.add(new Scout ( "Jean Kirschtein" , "Unranked" , "1" )	);
        scoutArrayList.add(new Scout ("Conny Springer", "Unranked" , "Unknown")	);
        scoutArrayList.add(new Scout ("Sasha Braus","Unranked","1")	);

    }
}
```

