<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Online Quiz System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
        }
        header {
            background: #0073e6;
            color: white;
            padding: 10px 20px;
            text-align: center;
        }
        .container {
            max-width: 800px;
            margin: 20px auto;
            background: white;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .btn {
            background: #0073e6;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .btn:hover {
            background: #005bb5;
        }
        .quiz-question {
            margin: 20px 0;
        }
        .quiz-options {
            list-style: none;
            padding: 0;
        }
        .quiz-options li {
            margin: 10px 0;
        }
    </style>
</head>
<body>
    <header>
        <h1>Online Quiz System</h1>
    </header>
    <div class="container" id="app">
        <div id="login-section">
            <h2>Login</h2>
            <form id="login-form">
                <label for="username">Username:</label>
                <input type="text" id="username" required><br><br>
                <label for="password">Password:</label>
                <input type="password" id="password" required><br><br>
                <button type="button" class="btn" onclick="login()">Login</button>
            </form>
        </div>
        <div id="quiz-section" style="display: none;">
            <h2>Quiz</h2>
            <div id="questions-container"></div>
            <button class="btn" onclick="submitQuiz()">Submit Quiz</button>
        </div>
        <div id="score-section" style="display: none;">
            <h2>Your Score</h2>
            <p id="score-text"></p>
            <button class="btn" onclick="resetQuiz()">Take Another Quiz</button>
        </div>
    </div>

    <script>
        const users = { admin: "admin123", student: "student123" };
        const questions = [
            {
                question: "What is the capital of France?",
                options: ["A) Paris", "B) Rome", "C) Berlin", "D) Madrid"],
                correct: "A"
            },
            {
                question: "Which programming language is used for Android development?",
                options: ["A) Python", "B) C++", "C) Java", "D) Kotlin"],
                correct: "C"
            },
            {
                question: "What is 5 + 3?",
                options: ["A) 9", "B) 8", "C) 7", "D) 10"],
                correct: "B"
            }
        ];
        let currentUser = null;
        let score = 0;

        function login() {
            const username = document.getElementById("username").value;
            const password = document.getElementById("password").value;

            if (users[username] === password) {
                currentUser = username;
                document.getElementById("login-section").style.display = "none";
                loadQuiz();
            } else {
                alert("Invalid username or password!");
            }
        }

        function loadQuiz() {
            document.getElementById("quiz-section").style.display = "block";
            const container = document.getElementById("questions-container");
            container.innerHTML = "";
            questions.forEach((q, index) => {
                const questionHTML = `
                    <div class="quiz-question">
                        <p>${index + 1}. ${q.question}</p>
                        <ul class="quiz-options">
                            ${q.options
                                .map(
                                    (option, i) =>
                                        `<li>
                                            <input type="radio" name="q${index}" value="${option.charAt(0)}" id="q${index}${i}">
                                            <label for="q${index}${i}">${option}</label>
                                        </li>`
                                )
                                .join("")}
                        </ul>
                    </div>`;
                container.innerHTML += questionHTML;
            });
        }

        function submitQuiz() {
            score = 0;
            questions.forEach((q, index) => {
                const selectedOption = document.querySelector(`input[name="q${index}"]:checked`);
                if (selectedOption && selectedOption.value === q.correct) {
                    score++;
                }
            });
            document.getElementById("quiz-section").style.display = "none";
            document.getElementById("score-section").style.display = "block";
            document.getElementById("score-text").textContent = `You scored ${score}/${questions.length}`;
        }

        function resetQuiz() {
            currentUser = null;
            score = 0;
            document.getElementById("score-section").style.display = "none";
            document.getElementById("login-section").style.display = "block";
        }
    </script>
</body>
</html>
