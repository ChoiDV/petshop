package com.lec.petshop.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.petshop.dao.DogDao;
import com.lec.petshop.dto.AdminDto;
import com.lec.petshop.dto.DogDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class DogModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("DogImageUpFolder");
		int maxSize = 1024 * 1024 * 10;
		String[] dimage = { "", "", "", "", "" };
		String[] originalDimage = { "", "", "", "", "" };
		try {
			MultipartRequest mRequest = new MultipartRequest(request, path, maxSize, "utf-8",
					new DefaultFileRenamePolicy());
			Enumeration<String> paramNames = mRequest.getFileNames();
			int idx = 0;
			while (paramNames.hasMoreElements()) {
				String param = paramNames.nextElement(); // 파라미터 이름 받아오기
				dimage[idx] = mRequest.getFilesystemName(param); // 그 파라미터 이름으로 저장된 파일을 가지고 옴
				originalDimage[idx] = mRequest.getOriginalFileName(param);// 해당 파라미터 이름으로 첨부된 오리지널 파일 이름 가지고 옴
				idx++;
			}
			DogDao dao = DogDao.getInstance();
			HttpSession session = request.getSession();
			int dnum = Integer.parseInt(mRequest.getParameter("dnum"));
			DogDto oldDog = dao.dogModifyContent(dnum);
			if(dimage[0] == null ) {
				dimage[0] = oldDog.getDimage1();
			}
			if(dimage[1] == null) {
				dimage[1] = oldDog.getDimage2();
			}
			if(dimage[2] == null) {
				dimage[2] = oldDog.getDimage3();
			} 
			if(dimage[3] == null) {
				dimage[3] = oldDog.getDimage4();
			} 
			if(dimage[4] == null) {
				dimage[4] = oldDog.getDimage5();
			}
//			for(int i = 0; i < dimage.length; i++) {
//				if(dimage[i] == null) {
//					dimage[i] = oldDog.getDimage+(i+1)();
//				}
//			}		
			String dname = mRequest.getParameter("dname");
			String dgender = mRequest.getParameter("dgender");
			String dbirthStr = mRequest.getParameter("dbirth");
			Date dbirth = null;
			if (!dbirthStr.equals("")) {
				dbirth = Date.valueOf(dbirthStr);
			}
			int dbreedno = Integer.parseInt(mRequest.getParameter("dbreedno"));
			int dprice = Integer.parseInt(mRequest.getParameter("dprice"));
			String dcontent = mRequest.getParameter("dcontent");
			
			String aid =  ((AdminDto) session.getAttribute("admin")).getAid();
			String dip = request.getRemoteAddr();
			DogDto dog = new DogDto(dnum, dname, dgender, dbirth, dprice, dbreedno, aid, dcontent, dimage[4],  dimage[3],  dimage[2],  dimage[1],  dimage[0], dip, 0, 1, null, null);
			int result = dao.updateDog(dog);
			if(result == DogDao.SUCCESS) {
				System.out.println(aid+" 관리자가 강아지 수정 성공");
				request.setAttribute("DogModifyResult", result);
			} else {
				System.out.println("등록 실패"+dog);
				request.setAttribute("DogModifyResult", result );
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		for (String f : dimage) {
			if (f != null) { // f가 null이 아닐때만 카피할 것
				// 파일복사
				InputStream is = null;
				OutputStream os = null;
				try {
					File serverfile = new File(path + "/" + f); // 첨부하지 않으면 filename에는 null이 들어감
					if (serverfile.exists()) { // 첨부한 파일이 있는지
						is = new FileInputStream(serverfile); // 원본 파일
						os = new FileOutputStream( "C:\\choijinyoung\\source\\petshop_project\\petshop\\WebContent\\DogImageUpFolder/" + f);// 복사될 파일
						byte[] bs = new byte[(int) serverfile.length()]; // 서버파일의 크기만큼 바이트로 읽어내겠따는 뜻, 형변환으로도int로 해주기
						while (true) {
							int nReadCnt = is.read(bs);
							if (nReadCnt == -1) {
								break;
							}
							os.write(bs, 0, nReadCnt);
						} // while
						System.out.println("서버 첨부 & 복사 완료");
					} // if
				} catch (Exception e) {
					System.out.println(e.getMessage());
				} finally {

					try {
						if (os != null) {
							os.close();
						}
						if (is != null) {
							is.close();
						}
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}

				}

			}
		}

	}

}
