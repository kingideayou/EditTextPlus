### EditTextPlus [![EditTextPlus](https://img.shields.io/badge/kingideayou-EditTextPlus-brightgreen.svg?style=flat)](https://github.com/kingideayou/EditTextPlus)

Easy way to add click or check button

### Attrs
button image resource:

	<attr name="button_img_normal" format="reference"/> 
checked button image resourece:

    <attr name="button_img_checked" format="reference"/>
### XML 
#### Add checkable button:
when use checkable style, inputType must be textPassword:

	<me.next.edittextplus.EditTextPlus
	        android:id="@+id/et_plus_checkable"
	        android:layout_width="match_parent"
	        android:layout_height="wrap_content"
	        android:inputType="textPassword"
	        app:etp_type="checkable"/>
	        
#### Add clickable button:
	        
    <me.next.edittextplus.EditTextPlus
        android:id="@+id/et_plus_clickable"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:inputType="text"
        app:button_img_normal="@drawable/delete_img_icon"
        app:etp_type="clickable"/>

### Code 
	((EditTextPlus) findViewById(R.id.et_plus_checkable)).setOnButtonClickListener(new EditTextPlus.OnButtonClickListener() {
	            @Override
	            public void onButtonClick() {
	                editTextPlusClickable.setText("");
	                Toast.makeText(getApplicationContext(), "click button", Toast.LENGTH_SHORT).show();
	            }
	        });

    ((EditTextPlus) findViewById(R.id.et_plus_checkable)).setOnButtonCheckListener(new EditTextPlus.OnButtonCheckListener() {
            @Override
            public void onButtonCheck(boolean isChecked) {
                Toast.makeText(getApplicationContext(), "isChecked : " + isChecked, Toast.LENGTH_SHORT).show();
            }
        });

### ScreenShot
![](http://ww3.sinaimg.cn/mw690/6db4aff6gw1f3spc414pmj21401z443y.jpg)

### Gif
![](http://ww4.sinaimg.cn/mw690/6db4aff6gw1f3sp83codrg20dw0op7ly.gif)

## License

    Copyright 2015 NeXT

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
