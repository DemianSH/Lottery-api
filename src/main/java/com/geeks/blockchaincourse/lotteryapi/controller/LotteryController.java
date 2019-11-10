package com.geeks.blockchaincourse.lotteryapi.controller;

import com.geeks.blockchaincourse.lotteryapi.controller.request.RequestModel;
import com.geeks.blockchaincourse.lotteryapi.model.LotteryInfo;
import com.geeks.blockchaincourse.lotteryapi.model.MemberLottery;
import com.geeks.blockchaincourse.lotteryapi.model.WinnerLottery;
import com.geeks.blockchaincourse.lotteryapi.service.LotteryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LotteryController {

    public LotteryService lotteryService;

    public LotteryController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @GetMapping("/info")
    public ResponseEntity<LotteryInfo> getLotteryInfo() {
        return new ResponseEntity<>(lotteryService.getInform(), HttpStatus.OK);
    }

    @GetMapping("/payments")
    public ResponseEntity<List<MemberLottery>> getActiveMember() {
        return new ResponseEntity<>(lotteryService.getActiveMembers(), HttpStatus.OK);
    }

    @GetMapping("/inactive-payments")
    public ResponseEntity<List<MemberLottery>> getInactiveMember() {
        return new ResponseEntity<>(lotteryService.getInactiveMembers(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MemberLottery>> getAll() {
        return new ResponseEntity<>(lotteryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/result")
    public ResponseEntity<WinnerLottery> getWinner() {
        return new ResponseEntity<>(lotteryService.getWinner(), HttpStatus.OK);
    }

    @PutMapping("/bet")
    public ResponseEntity postBet(@RequestBody RequestModel request) {
        if(isValidRequest(request)) {
            String trxHash = lotteryService.betLottery(request);
            return new ResponseEntity("Transaction hash: " + trxHash + "successfully sent", HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/close")
    public ResponseEntity closeLottery(@RequestBody RequestModel request) {
        if(isValidRequest(request)) {
            String trxHash = lotteryService.closeLottery(request);
            return new ResponseEntity( "Transaction hash: " + trxHash + " successfully sent", HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/kill")
    public ResponseEntity killContract(@RequestBody RequestModel request) {
        if(isValidRequest(request)) {
            String trxHash = lotteryService.killLottery(request);
            return new ResponseEntity( "Transaction hash: " + trxHash + " successfully sent", HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity handleExceptionPutMethods(RuntimeException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    private boolean isValidRequest(RequestModel request) {
        return (request == null || request.getAddress() ==null) ? false : true;
    }
}
