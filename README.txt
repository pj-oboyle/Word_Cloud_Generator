# Word Cloud Generator V1.0

This is a command-line menu-driven Java application that analyzes input text, calculates the word frequency, and generates a word cloud image of the most common words. It utilizes modular interfaces and multithreading to ensure flexibility and performance.

---

## Features

### **1. Core Functionalities**
- **Word Cloud Workflow:** The application uses five modular interfaces to create a word cloud step-by-step:
  1. **`Sourceable`**: Loads the input text (from a file, URL, etc.).
  2. **`Parseable`**: Prepares the text by isolating words, removing symbols/abbreviations, and ignoring specified words.
  3. **`Mappable`**: Maps the frequency of each word.
  4. **`Rankable`**: Ranks words based on decreasing frequency.
  5. **`Drawable`**: Outputs the word cloud image as an image file.

- **Input Source Flexibility:**
  - Accepts input from `.html` files, `.txt` files, and URLs (must include `http`/`https` protocol).
  - Automatically appends missing file extensions to input filenames when required.

- **Multi-Threading Options:**
  - **Unthreaded Mode:** Processes word frequency mapping in a single thread.
  - **Set Thread Number:** Allows the user to specify the number of threads for processing.
  - **Optimized Mode:** Automatically determines the optimal number of threads based on the number of available processors.

- **Error Handling:**
  - Custom error messages for invalid inputs (e.g., out-of-range integers, invalid file names, empty entries).
  - Verifies the existence of input sources before processing.

- **Output Features:**
  - Automatically names output images by concatenating input file name, filter name, and edge mode used.
  - Ensures output images match the file type of the input source.

### **2. User Interface**
- **Command-Line Menu:**
  - Main menu options for running the program, setting threading preferences, selecting input sources, and quitting.
  - Sub-menus allow navigation between input source options, threading settings, and main menu.
  - Program runs until a word cloud is generated or the user chooses to quit.

### **3. Specialized Classes**
- **`Menu` Class:**
  - Provides the interactive command-line interface for user input and settings management.

- **`ThreadedMapByHash` Class:**
  - Extends the `MapByHash` class for concurrent execution of word frequency mapping using `Runnable` tasks.

- **Input Source Processors:**
  - **`SourceURL`**: Processes text input from a URL.
  - **`SourceTxt`**: Processes text input from a `.txt` file.

- **ENUMs for Constants:**
  - Stores predefined font types and colors for word cloud generation.

---

## Installation

### **Prerequisites**
- **Java Development Kit (JDK):** Version 8 or above.
- **Integrated Development Environment (IDE):** Eclipse, IntelliJ IDEA, or a text editor with Java support.

### **Steps**
1. Clone the repository or download the source code.
   ```bash
   git clone https://github.com/your-username/word-cloud-generator.git
   ```
2. Open the project in your preferred IDE.
3. Compile the Java files:
   ```bash
   javac *.java
   ```
4. Run the program by executing the `Runner` class.

---

## Usage

### **Running the Program**
1. Select **Option 1: Run Program** from the main menu.
2. Set the number of most common words to display in the word cloud (if not already set).
3. Specify the input text source (file name, URL, etc.).
4. Verify the source details and execute the program.
5. View output messages and check the generated word cloud image.

### **Threading Options**
Access threading settings via **Main Menu > Option 2:**
1. **Unthreaded Mode:** No multithreading used.
2. **Set Thread Number:** Specify the number of threads to utilize.
3. **Optimized Mode:** Automatically optimizes threading based on system processors.

### **Input Source Options**
Navigate to **Main Menu > Option 3** to switch between:
1. `.html` files.
2. `.txt` files.
3. URLs (must include `http`/`https`).

---

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Contact
For further information, contact:
- **Name:** pj-oboyle
- **GitHub:** [pj-oboyle](https://github.com/pj-oboyle)

---

Thank you for using the Word Cloud Generator! If you find this project helpful, please give it a star on GitHub.

