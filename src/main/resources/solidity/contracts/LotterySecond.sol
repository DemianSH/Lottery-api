pragma solidity ^0.5.8;

contract LotterySecond {

    address payable owner;
    Member private winner;

    mapping (uint => Member ) participants;

    uint private countId;

    uint constant private limitBet = 10;
    uint private amountBets;

    uint private timeActivity;
    bool private lotteryStatus;

    struct Member {
        address member;
        uint txValue;
        uint txTime;
        bool memberStatus;
    }

    constructor() public {
        owner = msg.sender;
        lotteryStatus = true;
        timeActivity = now + 604800; //7 days = 604800 seconds
    }

    modifier onlyOwner() {
        require(msg.sender == owner, "You don't have acces");
        _;
    }

    function getInformationAboutLottery() public view returns(address, address, uint, bool) {
        return (owner, address(this), timeActivity, lotteryStatus);
    }

    function getAllParticipants() external view returns(address[] memory,
                                                        uint[] memory,
                                                        uint[] memory,
                                                        bool[] memory ) {
        address[] memory participantsAddress = new address[](countId);
        uint[] memory participantsTxValue = new uint[](countId);
        uint[] memory participantsTxTime = new uint[](countId);
        bool[] memory participantsStatus = new bool[](countId);

        for(uint i = 0; i < countId; i++) {
            participantsAddress[i] = participants[i].member;
            participantsTxValue[i] = participants[i].txValue;
            participantsTxTime[i] = participants[i].txTime;
            participantsStatus[i] = participants[i].memberStatus;
        }
        return(participantsAddress,
                participantsTxValue,
                participantsTxTime,
                participantsStatus);
    }


    function getWinner() public view returns(address, uint, uint) {
        address winnerAddress = winner.member;
        uint prize = amountBets/2;
        uint time = timeActivity;
        return (winnerAddress, prize, time);
    }

    function bet() external payable {
        assert(countId != limitBet);
        require(timeActivity > now, "Sorry, but time of lottery has finished");

        participants[countId] = _memberBuidl(msg.sender, msg.value, now);
        if(msg.value >= 0.2 ether) {
            participants[countId].memberStatus = true;
        }
        countId++;
        amountBets += msg.value;
    }

    function _memberBuidl(address sender, uint value, uint time) private pure returns(Member memory) {
        Member memory m;
        m.member = sender;
        m.txValue = value;
        m.txTime = time;
        return m;
    }

    function closeLottery() external payable onlyOwner {
        require(countId > 0, "The lottery doesn't have participants! The lottery hasn't finished yet");
        lotteryStatus = false;

        Member[] memory goodParticipants = new Member[](countId);
        uint count = 0;
        for(uint i = 0; i < countId; i++) {
            if(participants[i].memberStatus == true) {
                goodParticipants[count] = participants[i];
                count++;
            }
        }

        uint winNumb = _getRandomNumber(now, block.difficulty, count);
        winner = goodParticipants[winNumb];
        winner.txValue = amountBets/2;
        winner.txTime = now;
    }

    function _getRandomNumber(uint _a, uint _b, uint _multiplicity) private pure returns(uint) {
        return uint(keccak256(abi.encodePacked(_a, _b))) % _multiplicity;
    }

    function kill() external onlyOwner {
        selfdestruct(owner);
    }
}

