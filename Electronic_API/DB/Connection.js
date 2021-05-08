const mongoose = require('mongoose');

const URI = "mongodb+srv://root:Nguyenvanha1108@freecluster.bmexm.mongodb.net/electroniccommunication?retryWrites=true&w=majority";

const connectDB  = async() => {
    await mongoose.connect(URI, {
        useUnifiedTopology: true,
        useNewUrlParser: true
    })
    console.log("Db connect...!");
};

module.exports = connectDB;
