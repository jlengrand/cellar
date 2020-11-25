module.exports = async function (context, req) {
    context.log('JavaScript HTTP trigger function processed a request.');
    
    const lastTimestamp = (req.body || {})._start;
    const humidity = (req.body || {}).humidity;
    const reading = (req.body || {}).reading;

    var message = {
        "personalizations": [ { "to": [ { "email": "jlengrand@gmail.com" } ] } ],
       from: { email: "jlengrand@gmail.com" },
       subject: `Cellar Monitoring is down! Last ${reading} value read ${humidity} at ${lastTimestamp}`,
       content: [{
           type: 'text/plain',
           value: JSON.stringify(req.body)
       }]
   };

   context.done(null, message);
}