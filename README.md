# Capstone-Project
Currently, between 2 and 3 million non-melanoma skin cancers and 132,000 melanoma skin cancers occur globally each year (WHO, 2017). Skin cancer is one type of cancer that can cause death for many people. Because of this, an application is needed to easily detect skin cancer early that the cancer can be handled with more quickly. Besides, consultations with dermatologists have better prognosis (Avilés-Izquierdo et. al., 2016). Due to that, we built an early skin cancer detection application with dermatologist consultation.

Tech Stack:<br />
**Android Developer**<br /> 
Create user activity (such as login and register)<br /> 
Create home activity <br /> 
Implement buttons directs to diagnosis and doctor activity<br /> 
Create diagnosis activity<br /> 
Deploy tensorflow lite which made by machine learning<br /> 
Create intent camera and share to whatsapp<br /> 
Create doctor activity<br /> 
Deploy database on firebase for doctor and prediction which made by cloud<br /> 

- Kotlin, Android Studio
- Firebase Firestore & Auth -> Server-Side Deployment on the latest build version.
- TensorFlow Lite --> Client-Side Deployment on the previous build version.

![image](https://user-images.githubusercontent.com/56616689/120763740-b8dd7780-c541-11eb-88ae-fb9f439cffe2.png)
![image](https://user-images.githubusercontent.com/56616689/120763777-c266df80-c541-11eb-8edd-c9f7c0a44047.png)
![image](https://user-images.githubusercontent.com/56616689/120763825-cdba0b00-c541-11eb-89b3-d21868f671f7.png)
![image](https://user-images.githubusercontent.com/56616689/120763877-dad6fa00-c541-11eb-9e12-0f29505b93a9.png)
![image](https://user-images.githubusercontent.com/56616689/120763924-e75b5280-c541-11eb-96f2-faba48bbcfb0.png)
![image](https://user-images.githubusercontent.com/56616689/120763637-9ea39980-c541-11eb-82c7-a44566521893.png)

Example result


![image](https://user-images.githubusercontent.com/56616689/120764254-31443880-c542-11eb-8922-c8e02d97cd08.png)
![image](https://user-images.githubusercontent.com/56616689/120764067-0823a800-c542-11eb-8681-32b2f4114ad4.png)
![image](https://user-images.githubusercontent.com/56616689/120764136-15d92d80-c542-11eb-9358-61bb524a4a18.png)

**Machine Learning Develeoper**<br /> 

Using dataset from https://www.kaggle.com/kmader/skin-cancer-mnist-ham10000 <br /> 
Preprocessing csv data and split into 7 classes <br /> 
split train 85% and test 15% <br /> 
oversampling train data with image augmentation <br />
![image](https://github.com/softmaxbangkit/Capstone-Project/blob/eeb05c0bfecc3a418f4d56af4325cc543db84fd6/Machine%20Learning%20Screenshot/Train%20Test%20Split%20Augmentation.jpg)

Using InceptionResNetV2 for Transfer Learning <br /> 
Rescalling on top of InceptionResNet and add Flatten and Dense at bottom layer <br /> 
![image](https://github.com/softmaxbangkit/Capstone-Project/blob/eeb05c0bfecc3a418f4d56af4325cc543db84fd6/Machine%20Learning%20Screenshot/Parameter.jpg)

Training data <br />
![image](https://github.com/softmaxbangkit/Capstone-Project/blob/eeb05c0bfecc3a418f4d56af4325cc543db84fd6/Machine%20Learning%20Screenshot/Graph%20per%20epoch.jpg)

Predict list <br />
![image](https://github.com/softmaxbangkit/Capstone-Project/blob/eeb05c0bfecc3a418f4d56af4325cc543db84fd6/Machine%20Learning%20Screenshot/Predict%20List.jpg)

Save  model using SavedModel.pb <br /> 
convert SavedModel.pb into .tflite <br /> 

Example Result

![image](https://github.com/softmaxbangkit/Capstone-Project/blob/eeb05c0bfecc3a418f4d56af4325cc543db84fd6/Machine%20Learning%20Screenshot/Result%201.jpg)

**Cloud Computing**<br /> 
- Create database in cloud firestore<br /> 
![image](https://github.com/softmaxbangkit/Capstone-Project/blob/main/Cloud%20Computing%20Screenshot/Firestore1.jpg)
![image](https://github.com/softmaxbangkit/Capstone-Project/blob/eeb05c0bfecc3a418f4d56af4325cc543db84fd6/Cloud%20Computing%20Screenshot/Firestore2.jpg)
![image](https://github.com/softmaxbangkit/Capstone-Project/blob/eeb05c0bfecc3a418f4d56af4325cc543db84fd6/Cloud%20Computing%20Screenshot/Firestore3.jpg)
