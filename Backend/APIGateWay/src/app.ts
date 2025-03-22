import dotenv from 'dotenv';
import express,{ Application,Express,Request,Response} from 'express';

dotenv.config();

const app : Application =express();

const port = process.env.PORT || 3000;

app.get('/',(req:Request,res:Response)=>{
    res.send('Hello World');
});

app.listen(port,()=>`Server is running`);
