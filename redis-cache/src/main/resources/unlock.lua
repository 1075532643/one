---
--- Generated by EmmyLua(https://github.com/EmmyLua)
--- Created by chenyongguang.
--- DateTime: 2022/10/25 12:28
---
--获取锁中的线程标识 get key

--比较线程标识与锁中的标识是否一致
if(redis.call('get',KEY[1])== ARGV[1]) then
    --释放锁del key
    return redis.call('del',KEY[1])
end
return 0