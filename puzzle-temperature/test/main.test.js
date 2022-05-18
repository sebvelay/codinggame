const { expect, it } = require('@jest/globals');
const closerToZero = require('../src/main.js');

it('closer to 0 with value string',()=>{
    expect(closerToZero(10,['2','3','1','4'])).toBe("1");
});

it('closer to 0',()=>{
    expect(closerToZero(10,[2,3,-1,4])).toBe(-1);
});

it('closer to 0',()=>{
    expect(closerToZero(1000000,[1,-2,-8,4,5])).toBe(1);
});

it('closer to 0 should take the positive nombre',()=>{
    expect(closerToZero(1000000,[42,5,-5,12,42])).toBe(5);
});

it('closer to 0 with one value',()=>{
    expect(closerToZero(-273,[-273])).toBe(-273);
});

it('return 0 when no value',()=>{
    expect(closerToZero('',[''])).toBe(0);
});
