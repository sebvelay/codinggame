function closerToZero(a, b) {
    if(b === undefined || !Number.isInteger(parseInt(a))){
        return 0;
    }
    if (b.length > 0) {

        if(Math.abs(a)==Math.abs(b[0]) && a !== b[0]){
            return closerToZero(Math.abs(a), b.slice(1));
        }else if (Math.abs(a) < Math.abs(b[0])) {
            return closerToZero(a, b.slice(1));
        } else {
            return closerToZero(b[0], b.slice(1));
        }
    }
    else return a;
}



module.exports = closerToZero;