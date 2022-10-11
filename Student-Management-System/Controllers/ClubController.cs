using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using StudentManagementSystem.Models.EntityFramework;

namespace StudentManagementSystem.Controllers
{
    public class ClubController : Controller
    {
        // GET: Club
        DbSchoolEntities db = new DbSchoolEntities();
        public ActionResult Index()
        {
            var clb = db.CLUBS.ToList();
            return View(clb);
        }

        [HttpGet]
        public ActionResult AddNewClub()
        {
            return View();
        }

        [HttpPost]
        public ActionResult AddNewClub(CLUBS p)
        {
            db.CLUBS.Add(p);
            db.SaveChanges();
            return View();
        }

        public ActionResult Delete(int id)
        {
            var club = db.CLUBS.Find(id);
            db.CLUBS.Remove(club);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        public ActionResult Update(int id)
        {
            var clb = db.CLUBS.Find(id);
            return View("Update",clb);
        }

        public ActionResult ClubUpdate(CLUBS p)
        {
            var clb = db.CLUBS.Find(p.CLUBID);
            clb.CLUBNAME = p.CLUBNAME;
            db.SaveChanges();
            return RedirectToAction("Index");
        }
    }
}