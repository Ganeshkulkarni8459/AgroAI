import tensorflow as tf
from tensorflow.keras.preprocessing import image
import numpy as np

def load_model(model_path):
    """Load the Keras model."""
    return tf.keras.models.load_model(model_path)

def preprocess_image(image_file):
    """Load and preprocess the image for prediction."""
    img = image.load_img(image_file, target_size=(224, 224))  # Resize image to match model input size
    img_array = image.img_to_array(img)  # Convert the image to an array
    img_array = np.expand_dims(img_array, axis=0)  # Add batch dimension
    img_array /= 255.0  # Normalize the image
    return img_array

def predict_sugarcane_disease(image_file):
    model = tf.keras.models.load_model('sugarcane_disease_model.h5')
    CLASS_LABELS = {0: 'Healthy', 1: 'Mosaic', 2: 'RedRot', 3: 'Rust', 4: 'Yellow'}

    # Load and preprocess the image
    img = image.load_img(image_file, target_size=(128, 128))  # Resize image to match model input size
    img_array = image.img_to_array(img)  # Convert the image to an array
    img_array = np.expand_dims(img_array, axis=0)  # Add batch dimension
    img_array /= 255.0  # Normalize the image

    # Step 2: Use the model to make a prediction
    predictions = model.predict(img_array)
    predicted_class = np.argmax(predictions, axis=1)[0]  # Get the index of the highest probability
    confidence = np.max(predictions)  # Get the confidence

    return {'disease': CLASS_LABELS[predicted_class], 'plant_name' : "sugarcane"}

def predict_soybean_disease(image_file):
    model = load_model('soybean_disease_model.h5')  # Adjust the model name accordingly
    CLASS_LABELS = {0: 'Healthy', 1: 'Frogeye Leaf Spot', 2: 'Powdery Mildew', 3: 'Bacterial Blight'}

    img_array = preprocess_image(image_file)

    # Make a prediction
    predictions = model.predict(img_array)
    predicted_class = np.argmax(predictions, axis=1)[0]  # Get the index of the highest probability
    confidence = np.max(predictions)  # Get the confidence

    return {'disease': CLASS_LABELS[predicted_class], 'plant_name' : "soybean"}

def predict_cotton_disease(image_file):
    model = load_model('cotton_disease_model.h5')  # Adjust the model name accordingly
    CLASS_LABELS = {0: 'Healthy', 1: 'Cotton Leaf Curl Virus', 2: 'Boll Rot', 3: 'Alternaria Leaf Spot'}

    img_array = preprocess_image(image_file)

    # Make a prediction
    predictions = model.predict(img_array)
    predicted_class = np.argmax(predictions, axis=1)[0]  # Get the index of the highest probability
    confidence = np.max(predictions)  # Get the confidence

    return {'disease': CLASS_LABELS[predicted_class], 'plant_name' : "cotton"}
