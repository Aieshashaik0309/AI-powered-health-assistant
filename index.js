const express = require('express');
const bodyParser = require('body-parser');

const app = express();

// Middleware to parse JSON bodies
app.use(bodyParser.json());

// Health symptom checker (simplified example)
function checkSymptom(symptom) {
    const symptomsDict = {
        'fever': 'You may have a viral or bacterial infection. Consider seeing a doctor.',
        'headache': 'This could be a result of stress, dehydration, or a cold. Take some rest and hydrate.',
        'cough': 'Could be a common cold or respiratory infection. Stay hydrated and rest.',
        'fatigue': 'Fatigue can be due to lack of sleep, stress, or other underlying conditions. Rest and review your daily habits.',
    };

    const symptomLower = symptom.toLowerCase();

    for (const key in symptomsDict) {
        if (symptomLower.includes(key)) {
            return symptomsDict[key];
        }
    }
    return "It’s a good idea to consult a healthcare professional for a more accurate diagnosis.";
}

// Health tips based on user input
function healthTips(userQuery) {
    if (userQuery.toLowerCase().includes('exercise')) {
        return "Regular exercise improves cardiovascular health and boosts mood. Try to engage in physical activity 3-5 times a week.";
    } else if (userQuery.toLowerCase().includes('diet')) {
        return "A balanced diet rich in fruits, vegetables, whole grains, and lean proteins helps maintain overall health.";
    } else if (userQuery.toLowerCase().includes('stress')) {
        return "Consider mindfulness exercises or meditation. Regular breaks and talking to someone can also help.";
    }
    return "I recommend seeking personalized advice from a health expert.";
}

// Endpoint to handle health queries
app.post('/health_assistant', (req, res) => {
    const userInput = req.body.query;

    if (!userInput) {
        return res.json({ response: "Please provide a valid query." });
    }

    // Check for health-related advice or symptoms
    if (userInput.toLowerCase().includes('symptom')) {
        const symptom = userInput.split('symptom:')[1]?.trim();
        if (symptom) {
            const response = checkSymptom(symptom);
            return res.json({ response });
        }
    }

    const response = healthTips(userInput);
    return res.json({ response });
});

// Start the server
const port = 3000;
app.listen(port, () => {
    console.log(`Health assistant is running on http://localhost:${port}`);
});
