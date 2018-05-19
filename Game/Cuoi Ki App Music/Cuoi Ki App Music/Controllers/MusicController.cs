using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Cuoi_Ki_App_Music.Controllers
{
    public class MusicController : ApiController
    {
        [HttpGet]
        public List<Account> GetAccList()
        {
            DBMusicDataContext db = new DBMusicDataContext();
            return db.Accounts.ToList();

        }
        
        [HttpGet]
        public Account GetAcc(string nameacc)
        {
            DBMusicDataContext db = new DBMusicDataContext();
            return db.Accounts.FirstOrDefault(x => x.user == nameacc);
        }
       
        [HttpPost]
        public bool InsertAcc(string name, string pass)
        {
            try
            {
                DBMusicDataContext db = new DBMusicDataContext();
                Account acc = new Account();
                acc.user = name;
                acc.passwords = pass;
                db.Accounts.InsertOnSubmit(acc);
                db.SubmitChanges();
                return true;
            }
            catch
            {
                return false;
            }
        }
        [HttpPut]
        public bool UpdateAcc(string user, string pass)
        {
            {
                try
                {
                    DBMusicDataContext db = new DBMusicDataContext();
                    //lấy food tồn tại ra
                    Account acc = db.Accounts.FirstOrDefault(x => x.user == user);
                    if (acc == null) return false;//không tồn tại false
                    acc.passwords = pass;
                    db.SubmitChanges();//xác nhận chỉnh sửa
                    return true;
                }
                catch
                {
                    return false;
                }
            }
        }
     
        [HttpDelete]
        public bool DeleteAcc(string usernamedelacc)
        {
            DBMusicDataContext db = new DBMusicDataContext();
            Account acc = db.Accounts.FirstOrDefault(x => x.user == usernamedelacc);
            if (acc == null) return false;
            db.Accounts.DeleteOnSubmit(acc);
            db.SubmitChanges();
            return true;
        }
    }
}