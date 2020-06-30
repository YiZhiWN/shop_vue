package com.example.housework.Service;

import com.example.housework.mapper.AskMapper;
import com.example.housework.util.Redis.RedisUtil;
import com.example.housework.util.VSM.Vsm;
import com.example.housework.vo.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AskService {

    @Autowired
    RedisUtil redisUtil;
    @Autowired
    Vsm vsm;
    @Autowired
    AskMapper askMapper;

    public Bean ask(String question){
        //先做分词的处理,计算tf
        Map<String,Double> tfMap = vsm.tf(question);
        //从redis取出对应idf,计算出w
        Map<String,Double> wMap = new HashMap<>();
        for(String a : tfMap.keySet()){
            if(redisUtil.get(a) == null){
                break;
            }
            double w = tfMap.get(a) * (double)redisUtil.get(a);
            wMap.put(a,w);
        }
        //比对w,取出对应w最大的id
        int max = (int)redisUtil.get("max");
        int min = (int)redisUtil.get("min");
        double sim = 0;
        int index = 0;
        while(min <= max){
            Map<String,Double> minW = (HashMap<String,Double>)redisUtil.get(String.valueOf(min));
            if(minW == null){
                min ++;
                continue;
            }
            double value = vsm.calCosSim(wMap,minW);
            if(value>sim){
                sim = value;
                index = min;
            }
            min ++;
        }
        String answer = askMapper.answer(index);
        Bean<String> res = new Bean<>();
        res.setRespCode(0);
        res.setMessage("success");
        res.setData(answer);
        return res;
    }
}
